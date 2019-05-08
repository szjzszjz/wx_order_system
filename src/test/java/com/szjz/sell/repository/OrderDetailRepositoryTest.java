package com.szjz.sell.repository;

import com.szjz.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/8 16:58
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {


    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("123");
        orderDetail.setProductName("冰欺绫");
        orderDetail.setProductPrice(new BigDecimal(12));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("www.image.png");
        repository.save(orderDetail);

    }

    @Test
    public void findByOrOrderId(){
        List<OrderDetail> list = repository.findByOrOrderId("123");
        System.err.println(list);
    }

}