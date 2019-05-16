package com.szjz.sell.aspect;

import com.szjz.sell.constant.CookieConstant;
import com.szjz.sell.constant.RedisConstant;
import com.szjz.sell.exception.SellerAuthorizeException;
import com.szjz.sell.utils.CookieUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家用户登录授权切面
 * @author szjz
 * @date 2019/5/16 14:28
 */

@Aspect
@Slf4j
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //设置切点  对所有controller包下面的以seller开通的所有控制器里面的方法进行拦截  但是除了SellerUserController
    @Pointcut("execution(public * com.szjz.sell.controller.Seller*.*(..))"+
    "&& !execution(public * com.szjz.sell.controller.SellerUserController.*(..))" +

    "|| execution(public * com.szjz.sell.controller.Buyer*.*(..))"
    )
    public void verify(){ }

    //如果用户在没有登录的情况下通过链接直接访问系统
    //在拦截的方法之前执行此方法
    @Before("verify()")
    public void doVerify(){
        //获取request里面的cookie
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.error("【授权登录异常】 cookie中查不到token ");
            throw new SellerAuthorizeException();
        }

        //查询Redis中是否有token
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtil.isNullOrEmpty(tokenValue)){
            log.error("【授权登录异常】 redis中查不到token ");
            throw new SellerAuthorizeException();
        }

    }
}
