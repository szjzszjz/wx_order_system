package com.szjz.sell.enums;

import lombok.Getter;

/**
 * @author szjz
 * @date 2019/5/8 17:46
 */

@Getter
public enum ResultEnum {
    /** shift + ctrl + u 大小写相互转换*/
    PRODUCT_NOT_EXIST(0 ,"商品不存在"),
    PRODUCT_STOCK_ERROR(1, "商品库存不足"),
    ORDER_NOT_EXIST(2,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(2,"订单详情不存在"),
    ORDER_STATUS_ERROR(3,"订单状态异常"),
    ORDER_UPDATE_FAIL(4,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(5,"订单支付状态异常"),

    ORDER_CREATE_FAIL(6,"创建订单失败"),

    PARAMETER_ERROR(7,"参数不正确"),

    CAR_IS_EMPTY(8,"购物车为空"),

    RESULT_IS_EMPTY(9,"查询结果为空"),

    OWNER_IS_ERROR(10,"非当前登录用户"),

    WX_MP_ERROR(11,"微信公众号方面出现错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(12 ,"微信支付通知金额验证异常"),

    ORDER_CANCEL_SUCCESS(13,"订单取消成功"),
    ORDER_FINISH_SUCCESS(14,"订单完结成功")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
