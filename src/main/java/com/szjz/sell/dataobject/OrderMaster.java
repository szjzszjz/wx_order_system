package com.szjz.sell.dataobject;

import com.szjz.sell.enums.OrderStatusEnum;
import com.szjz.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author szjz
 * @date 2019/5/8 16:03
 * 订单
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster implements Serializable {


    private static final long serialVersionUID = 1405898827679807950L;
    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 默认新订单0
     */
    private Integer orderStatus = OrderStatusEnum.NWE.getCode();

    /**
     * 支付状态 默认等待支付0
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
