package com.szjz.sell.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 16:13
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "未支付"),
    SUCCESS(1, "已支付");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
