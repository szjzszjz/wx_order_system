package com.szjz.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.szjz.sell.dataobject.OrderDetail;
import com.szjz.sell.dataobject.OrderMaster;
import com.szjz.sell.enums.CodeEnum;
import com.szjz.sell.enums.OrderStatusEnum;
import com.szjz.sell.enums.PayStatusEnum;
import com.szjz.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 17:23
 * 订单数据传输对象 用于在内部传输 继承于订单类
 */

@Data

@JsonInclude(JsonInclude.Include.NON_NULL) //如果字段為null则不返回 给前端
public class OrderDTO extends OrderMaster {

    /** 订单id */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态 默认新订单0*/
    private Integer orderStatus;

    /** 支付状态 默认等待支付0*/
    private Integer payStatus;

    /** 添加订单详情列表 */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore  //json 转对象就会忽略掉带这个注解的属性
    public OrderStatusEnum getOrderStatusEnum(){
        return  EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
