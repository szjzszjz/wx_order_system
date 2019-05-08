package com.szjz.sell.repository;

import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.enums.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/8 16:37
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234");
        orderMaster.setBuyerName("小徐同学");
        orderMaster.setBuyerAdress("万胜围奔马");
        orderMaster.setBuyerPhone("12345678999");
        orderMaster.setBuyerOpenid("7717");
        orderMaster.setOrderAmount(new BigDecimal(30));
        orderMaster.setOrderStatus(OrderStatusEnum.NWE.getCode());
        repository.save(orderMaster);
        Assert.assertNotNull(orderMaster);


    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest pageRequest = PageRequest.of(0, 1);

        Page<OrderMaster> page = repository.findByBuyerOpenid("3126", pageRequest);

        System.err.println(page.getTotalPages());
    }

}