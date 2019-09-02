package com.szjz.sell.service;

import com.szjz.sell.dto.OrderDTO;

/**
 * 微信消息推送接口
 *
 * @author szjz
 * @date 2019/5/17 9:56
 */
public interface WXPushMessageService {
    /**
     * 向用户推送订单状态消息
     *
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
