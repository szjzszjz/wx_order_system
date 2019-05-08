package com.szjz.sell.exception;

import com.szjz.sell.enums.ResultEnum;

/**
 * @author szjz
 * @date 2019/5/8 17:45
 * 异常类
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());//将message传到父类的构造方法中
        this.code = resultEnum.PRODUCT_NOT_EXIST.getCode();
    }
}
