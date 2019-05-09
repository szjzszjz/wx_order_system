package com.szjz.sell.converter;

import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author szjz
 * @date 2019/5/9 11:00
 * 转换器   OrderMaster 转 OrderDTO
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                converter(e)
        ).collect(Collectors.toList());
    }
}
