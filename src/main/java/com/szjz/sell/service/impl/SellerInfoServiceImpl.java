package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.SellerInfo;
import com.szjz.sell.repository.SellerInfoRepository;
import com.szjz.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author szjz
 * @date 2019/5/15 19:33
 */

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo findByUsernameAndPassword(String username, String password) {
        return sellerInfoRepository.findByUsernameAndPassword(username,password);
    }
}
