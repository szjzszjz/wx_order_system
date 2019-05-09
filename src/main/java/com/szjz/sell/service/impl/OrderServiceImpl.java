package com.szjz.sell.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import com.szjz.sell.converter.OrderMaster2OrderDTOConverter;
import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.dto.CarDTO;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.enums.OrderStatusEnum;
import com.szjz.sell.enums.PayStatusEnum;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.repository.OrderDetailRepository;
import com.szjz.sell.repository.OrderMasterRepository;
import com.szjz.sell.repository.ProductInfoRepository;
import com.szjz.sell.service.OrderService;
import com.szjz.sell.service.ProductInfoService;
import com.szjz.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author szjz
 * @date 2019/5/8 17:33
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional //一旦发生异常回退 不扣除库存
    public OrderDTO createOrder(OrderDTO orderDTO) {

        //定义一个初始价格，用于计算总价格
        BigDecimal orderAmount = new BigDecimal(0);
        //生成订单id
        String orderId = KeyUtil.genUniqueKey();
        //1、查询商品（数量，价格）

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoRepository.findById(orderDetail.getProductId()).orElse(null);
            if (productInfo == null) {
                //抛出没有商品的异常
                //throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                return null;
            }

            //2、计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //3、订单详情入库(OrderDetail)
            orderDetail.setDetailId(KeyUtil.genUniqueKey());//设置订单详情id
            orderDetail.setOrderId(orderId);//设置订单ID 每个订单里包含n个订单详情 多个详情共用一个订单id
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }


        //4、订单入库（OrderMaster）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);//一般把拷贝属性写在上面 避免空属性 覆盖其他设置的属性
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NWE.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //重新将属性拷贝至orderDTO
        BeanUtils.copyProperties(orderMaster,orderDTO);
        //5、扣库存 (根据ID 减去数量)
        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CarDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(carDTOList);

        return orderDTO;
    }


    @Override
    public OrderDTO findById(String orderId) {
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderDTO);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList == null) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 获取某个用户的所有订单
     */
    @Override
    public Page<OrderDTO> findAll(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //通过自定义的转换器 将OrderMaster 转转为 OrderDTO
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /** 获取订单详情列表 */
    @Override
    public Page<OrderDetail> findOrderDetailList(String orderId, Pageable pageable) {
        Page<OrderDetail> page = orderDetailRepository.findByOrderId(orderId, pageable);
        return page;
    }

    /**
     * 取消订单
     */
    @Override
    @Transactional //添加事务
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        //判断订单状态 只有等于新订单的情况下才能取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NWE.getCode())){
            log.error("【取消订单】: 订单状态不正确 orderId={} ，orderStatus={}" ,orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态 保存至数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null){//对保存结果检测
            log.error("【取消订单】: 更新失败 orderMaster={}" ,orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        //检测商品详情是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】: 订单中没有商品详情 orderDetail={}" ,orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<CarDTO> carDTOList = orderDetailList.stream().map(e ->
                new CarDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(carDTOList);

        //如果已经支付需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //todo
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NWE.getCode())){
            log.error("【完结订单】: 订单状态不正确 orderId={} ，orderStatus={}" ,orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态 保存至数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null){//对保存结果检测
            log.error("【完结订单】: 更新失败 orderMaster={}" ,orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paidOrder(OrderDTO orderDTO) {
        //判断订单状态 只有新订单才能支付
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NWE.getCode())){
            log.error("【订单支付】: 订单状态不正确 orderId={} ，orderStatus={}" ,orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态 只有未支付状态才能支付
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】: 订单支付状态异常 orderPayStatus={}",orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result== null){//对保存结果检测
            log.error("【订单支付】: 更新失败 orderMaster={}" ,orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
