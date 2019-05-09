package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        orderDTO.setBuyerAddress("广州天河");
        orderDTO.setBuyerPhone("17613577717");


        List<OrderDetail> orderDetailList = new ArrayList<>();
        //客户买的第一份产品
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1232");
        orderDetail.setProductQuantity(3);

        //客户买的第二份产品
        OrderDetail orderDetail01 = new OrderDetail();
        orderDetail01.setProductId("1233");
        orderDetail01.setProductQuantity(3);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail01);
        
        //最后产生一份订单 两份订单详情
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO od = orderService.createOrder(orderDTO);
        log.info("【创建订单】：{}",od);
    }

    @Test
    public void findById() {
        OrderDTO orderDTO = orderService.findById("1557368577811");
        System.err.println(orderDTO);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> page = orderService.findAll("3126", request);
        System.err.println(page.getTotalPages());
        System.err.println(page.getTotalElements());
        System.err.println(page.getContent());
        System.err.println(page.getNumber());
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO = orderService.findById("123");
        OrderDTO orderDTO1 = orderService.cancelOrder(orderDTO);
        System.err.println(orderDTO1);
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = orderService.findById("1233");
        OrderDTO orderDTO1 = orderService.finishOrder(orderDTO);
        System.err.println(orderDTO1);
    }

    @Test
    public void paidOrder() {
        OrderDTO orderDTO = orderService.findById("1233");
        OrderDTO orderDTO1 = orderService.paidOrder(orderDTO);
        System.err.println(orderDTO1);
    }
}