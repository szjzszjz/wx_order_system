package com.szjz.sell.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 16:04
 * 订单状态枚举类
 */

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NWE(0, "新订单"),
    FINISH(1, "已完结"),
    CANCEL(2, "已取消");


    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
