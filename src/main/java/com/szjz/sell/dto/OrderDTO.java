package com.szjz.sell.dto;

import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dataobject.OrderMaster;
import lombok.Data;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 17:23
 * 订单数据传输对象 用于在内部传输 继承于订单类
 */

@Data
public class OrderDTO extends OrderMaster {

    /** 添加订单详情列表 */
    private List<OrderDetail> orderDetailList;

}
