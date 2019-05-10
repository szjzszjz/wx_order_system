package com.szjz.sell.controller;

import com.szjz.sell.converter.OrderForm2OrderMasterConverter;
import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.form.OrderForm;
import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.service.OrderService;
import com.szjz.sell.service.ProductInfoService;
import com.szjz.sell.utils.CheckOwnerUtil;
import com.szjz.sell.utils.ResultObjectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author szjz
 * @date 2019/5/9 14:34
 * 买家订单
 */

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductInfoService productInfoService;

    //创建订单 方法1
    @RequestMapping(value = "/create1", method = RequestMethod.POST)
    @ApiOperation(value = "创建订单 1", notes = "一次只能添加一个商品", response = ResultObject.class)
    public ResultObject create(@RequestParam String buyerOpenid,
                               @RequestParam String buyerName,
                               @RequestParam String buyerAddress,
                               @RequestParam String buyerPhone,
                               @RequestParam Integer productQuantity,
                               @RequestParam String productId) {

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid(buyerOpenid);
        orderMaster.setBuyerName(buyerName);
        orderMaster.setBuyerAddress(buyerAddress);
        orderMaster.setBuyerPhone(buyerPhone);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductQuantity(productQuantity);
        orderDetail.setProductId(productId);
        orderDetailList.add(orderDetail);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);
        if (result == null) {
            log.error("【创建订单】 创建订单失败 orderDTO=null");
            return ResultObjectUtil.error(404, "创建订单失败");
        }
        return ResultObjectUtil.success(result);
    }

    //创建订单 方法 2
    @RequestMapping(value = "/create2", method = RequestMethod.POST)
    @ApiOperation(value = "创建订单 2", notes = "一次可以添加多个商品", response = ResultObject.class)
    public ResultObject create(@Valid OrderForm orderForm,//订单表格验证
                               BindingResult bindingResult) {
        //首先对表单参数进行验证
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确 orderForm={}", orderForm);
            return ResultObjectUtil.error(
                    ResultEnum.PARAMETER_ERROR.getCode(), //
                    bindingResult.getFieldError().getDefaultMessage() //获取字段上的文字信息
            );
        }

        OrderDTO orderDTO = OrderForm2OrderMasterConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {

            log.error("【创建订单】 商品详情为空 detail={}", orderDTO.getOrderDetailList());
            /*throw new SellException(ResultEnum.CAR_IS_EMPTY);*/
            return ResultObjectUtil.error(ResultEnum.CAR_IS_EMPTY);
        }

        OrderDTO resultOrderDTO = orderService.createOrder(orderDTO);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("orderId", resultOrderDTO.getOrderId());
        return ResultObjectUtil.success(map);
    }

    //订单列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "订单列表", notes = "", response = ResultObject.class)
    public ResultObject getOrderList(@RequestParam(defaultValue = "0",required = false) Integer pageNumber, //
                                     @RequestParam(defaultValue = "10",required = false) Integer pageSize,//
                                     @RequestParam String buyerOpenid) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<OrderDTO> page = orderService.findAll(buyerOpenid, pageRequest);
        if (page.getContent().size()==0){
            return ResultObjectUtil.success(ResultEnum.RESULT_IS_EMPTY);
        }

        return ResultObjectUtil.success(page.getContent());
    }

    //订单详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "订单详情", notes = "", response = ResultObject.class)
    public ResultObject getOrderDetail(@RequestParam String orderId,
                                       @RequestParam String buyerOpenid){

        OrderDTO orderDTO = orderService.findById(orderId);
        if (!CheckOwnerUtil.check(buyerOpenid, orderDTO)){
            return ResultObjectUtil.error(ResultEnum.OWNER_IS_ERROR);
        }
        return ResultObjectUtil.success(orderDTO);
    }


    //取消订单
    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ApiOperation(value = "取消订单", notes = "", response = ResultObject.class)
    public ResultObject cancelOrder(@RequestParam String orderId,
                                    @RequestParam String buyerOpenid){
        OrderDTO or = orderService.findById(orderId);
        if (!CheckOwnerUtil.check(buyerOpenid, or)){
            return ResultObjectUtil.error(ResultEnum.OWNER_IS_ERROR);
        }
        orderService.cancelOrder(or);
        return ResultObjectUtil.success();
    }
}
