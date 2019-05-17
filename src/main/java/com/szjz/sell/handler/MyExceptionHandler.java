package com.szjz.sell.handler;

import com.szjz.sell.configuration.properties.ProjectUrlCofigProperty;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.exception.SellerAuthorizeException;
import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.utils.ResultObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常拦截处理  使返回前端的json格式统一
 * {
 * "code": 100,
 * "message": "xxxxxx",
 * "data": null
 * }
 *
 * @author szjz
 * @date 2019/5/16 15:15
 */

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @Autowired
    private ProjectUrlCofigProperty urlConfig;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultObject handler(Exception e) {
        if (e instanceof SellException) {
            log.error("{}: {}",e.getClass().getSimpleName(),e);  // 打印异常便于处理
            SellException sellException = (SellException) e;
            return ResultObjectUtil.error(sellException.getCode(), e.getMessage());
        }else {
            log.error("【系统异常】 {}",e);
            return ResultObjectUtil.error(-1, "未知异常");
        }
    }

    //拦截之后跳转到登录界面
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView authHandler(SellerAuthorizeException e){
        log.error("{}",e);
        return new ModelAndView("login/index");
    }
}
