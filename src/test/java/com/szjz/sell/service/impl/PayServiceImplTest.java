package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.repository.OrderDetailRepository;
import com.szjz.sell.service.OrderService;
import com.szjz.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/13 14:56
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findById("123");
        payService.create(orderDTO);
    }
}