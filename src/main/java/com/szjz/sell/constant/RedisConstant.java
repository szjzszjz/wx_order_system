package com.szjz.sell.constant;

/**
 * @author szjz
 * @date 2019/5/16 10:50
 * redis常量
 */
public class RedisConstant {

    /**
     * key值前缀
     */
    public static final String TOKEN_PREFIX = "token_%s";

    /**
     * 过期时间 单位为妙
     */
    public static final Integer EXPIRE = 7200;
}
