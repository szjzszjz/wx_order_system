package com.szjz.sell.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 10:29
 * 商品状态枚举类
 */

@Getter //生成get方法
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "上架"),
    DOWN(1, "下架");

    /**
     * 0 上架 1 下架
     */
    private Integer code;

    /**
     *
     */
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
