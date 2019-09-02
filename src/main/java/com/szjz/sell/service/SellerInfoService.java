package com.szjz.sell.service;

import com.szjz.sell.dataobject.SellerInfo;

/**
 * @author szjz
 * @date 2019/5/15 19:32
 */
public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenid(String openid);

    SellerInfo findByUsernameAndPassword(String username, String password);
}
