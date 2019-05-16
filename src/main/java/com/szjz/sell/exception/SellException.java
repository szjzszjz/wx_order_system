package com.szjz.sell.exception;

import com.szjz.sell.enums.ResultEnum;
import lombok.Data;

/**
 * @author szjz
 * @date 2019/5/8 17:45
 * 异常类
 */

@Data
public class SellException extends RuntimeException{

    public Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());//将message传到父类的构造方法中
        this.code = resultEnum.PRODUCT_NOT_EXIST.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);//将message传到父类的构造方法中
        this.code = code;
    }
}
