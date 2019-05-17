package com.szjz.sell.service.impl;

import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.service.OrderService;
import com.szjz.sell.service.WXPushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/17 10:57
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WXPushMessageServiceImplTest {

    @Autowired
    private WXPushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {

        OrderDTO orderDTO = orderService.findById("1557392016154");
        System.err.println(orderDTO);
        pushMessageService.orderStatus(orderDTO);

    }
}