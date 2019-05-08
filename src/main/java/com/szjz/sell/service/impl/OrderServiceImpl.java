package com.szjz.sell.service.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author szjz
 * @date 2019/5/8 17:33
 */

@Service
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

        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoRepository.findById(orderDetail.getProductId()).orElse(null);
            if (productInfo == null){
                //抛出没有商品的异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2、计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //3、订单详情入库(OrderDetail)
            orderDetail.setDetailId(KeyUtil.genUniqueKey());//设置订单详情id
            orderDetail.setOrderId(orderId);//设置订单ID 每个订单里包含n个订单详情 多个详情共用一个订单id
            BeanUtils.copyProperties(productInfo,orderDetail);
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

        //5、扣库存 (根据ID 减去数量)
        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CarDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(carDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findAll(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paidOrder(OrderDTO orderDTO) {
        return null;
    }
}
