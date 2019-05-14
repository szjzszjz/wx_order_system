package com.szjz.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.szjz.sell.dto.OrderDTO;

/**
 * @author szjz
 * @date 2019/5/13 14:49
 *
 * 支付接口
 */
public interface PayService {
    /** 创建支付 */
    PayResponse create(OrderDTO orderDTO);
    /** 异步支付通知 */
    PayResponse notify(String notifyData);
    /** 退款 */
    RefundResponse refund(OrderDTO orderDTO);
}
