package com.szjz.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szjz
 * @date 2019/5/9 16:06
 * 订单验证类 转 订单类
 */

@Slf4j
public class OrderForm2OrderMasterConverter {

    public static OrderDTO converter(OrderForm orderForm) {

        Gson gson = new Gson();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】 对象转换错误 class=OrderForm2OrderMasterConverter");
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

}
