package com.szjz.sell.repository;

import com.szjz.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void findByOrderId(){
        List<OrderDetail> list = repository.findByOrderId("123");
        System.err.println(list);
    }

    @Test
    public void get1() {

        ArrayList<Integer> cookies = new ArrayList<>();
        for (int i = 0; i <100000; i++) {
            cookies.add(i);
        }

        long startMillis1 = System.currentTimeMillis();
        for (int integer : cookies) {
            if (integer==99999) {
                System.err.println("yes");
            }
        }
        System.err.println("用for循环遍历耗时0：" + (System.currentTimeMillis() - startMillis1) + "ms");
    }

    @Test
    public void getTest1() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<Integer> cookies = new ArrayList<>();

        for (int i = 0; i <100000; i++) {
            cookies.add(i);
        }

        long startMillis1 = System.currentTimeMillis();
        if(cookies != null){
            for (int integer : cookies) {
                map.put(String.valueOf(integer), integer);
            }
        }

        if(map.containsKey(String.valueOf(99999))){
            System.err.println("yes");
        }else {
            System.err.println("no");
        }
        System.err.println("用for循环遍历耗时1：" + (System.currentTimeMillis() - startMillis1) + "ms");
    }
}