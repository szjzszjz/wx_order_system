package com.szjz.sell.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 17:46
 */

@Getter
public enum ResultEnum {
    /** shift + ctrl + u 小写变大写 */
    PRODUCT_NOT_EXIST(0 ,"商品不存在"),
    PRODUCT_STOCK_ERROR(1, "商品库存不足")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
