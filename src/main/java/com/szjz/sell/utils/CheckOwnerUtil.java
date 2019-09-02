package com.szjz.sell.utils;

import com.szjz.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author szjz
 * @date 2019/5/9 18:47
 * 用于检验是否是当前用户
 */
@Slf4j
public class CheckOwnerUtil {

    public static Boolean check(String buyerOpenid, OrderDTO orderDTO) {
        if (buyerOpenid.equals(orderDTO.getBuyerOpenid())) {
            return true;
        } else {
            log.error("【当前用户判断】 非系统当前登录用户");
            return false;
        }
    }
}
