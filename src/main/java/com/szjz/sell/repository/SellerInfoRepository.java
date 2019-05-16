package com.szjz.sell.repository;

import com.szjz.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author szjz
 * @date 2019/5/15 19:23
 *
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
    SellerInfo findByUsernameAndPassword(String username,String password);
}
