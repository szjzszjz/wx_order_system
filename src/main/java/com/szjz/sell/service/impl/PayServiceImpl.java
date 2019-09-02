package com.szjz.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.service.OrderService;
import com.szjz.sell.service.PayService;
import com.szjz.sell.utils.JsonUtil;
import com.szjz.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.lly835.bestpay.enums.BestPayTypeEnum.WXPAY_H5;

/**
 * @author szjz
 * @date 2019/5/13 14:51
 */

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    private final String PAY_NAME = "微信订单支付";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(PAY_NAME);
        payRequest.setPayTypeEnum(WXPAY_H5);
        log.info("【微信支付】 请求支付 payRequest={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 响应支付 payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {

        //1、验证签名
        //2、支付的状态
        //-----前两个由sdk完成
        //3、支付金额
        //-----主要是验证支付金额
        //4、支付人（下单人 是否等于 支付人）
        //-----默认下单人== 支付人

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知 ayResponse={}", JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.findById(payResponse.getOrderId());
        //判断订单是否存在
        if (orderDTO == null) {
            log.error("【微信支付】 异步通知 订单不存在 ，orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致 bigDecimal类型不能和double类型直接比较  转换成bigDecimal类型 但是bigdecimal 类型
        //后面还会有很多的未知小数，double和bigdecimal依然不能完全相等  这时利用两值的差小于某个值来判断
        if (!MathUtil.equal(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("【微信支付】 异步通知 订单金额不一致 orderId={} ,微信通知金额={}, 系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.paidOrder(orderDTO);
        return payResponse;
    }

    /**
     * 退款   利用sdk 只传递以下三个参数
     * "payTypeEnum": "WXPAY_H5",
     * "orderId": "123",
     * "orderAmount": 30.0
     *
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 退款请求 refundRequest={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 退款响应 refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
