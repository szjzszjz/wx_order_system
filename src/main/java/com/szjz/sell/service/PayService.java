package com.szjz.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.szjz.sell.dto.OrderDTO;

/**
 * @author szjz
 * @date 2019/5/13 14:49
 *
 * 支付接口
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

}
