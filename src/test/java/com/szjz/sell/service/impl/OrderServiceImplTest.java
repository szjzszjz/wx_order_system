package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/8 19:28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final static String  BUYER_OPENID = "3126";

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerName("石中君子");
        orderDTO.setBuyerAdress("广州天河");
        orderDTO.setBuyerPhone("17613577717");
        //orderDTO.setOrderId("123");


        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(3);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO od = orderService.createOrder(orderDTO);
        log.info("【创建订单】：{}",od);
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void finishOrder() {
    }

    @Test
    public void paidOrder() {
    }
}