package com.szjz.sell.service;

import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author szjz
 * @date 2019/5/8 17:14
 */
public interface OrderService {

    /** 创建订单 */
    OrderDTO createOrder(OrderDTO orderDTO);

    /** 查询单个订单 */
    OrderDTO findById(String orderId);

    /** 查询某个用户的订单列表 */
    Page<OrderDTO> findAll(String buyerOpenid ,Pageable pageable);

    /** 取消订单 */
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /** 完结订单 */
    OrderDTO finishOrder(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paidOrder(OrderDTO orderDTO);


}
