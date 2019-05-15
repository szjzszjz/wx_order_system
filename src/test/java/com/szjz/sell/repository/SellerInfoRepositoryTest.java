package com.szjz.sell.repository;

import com.szjz.sell.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/15 19:26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void findByOpAndOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("3126");
        System.out.println(sellerInfo);

    }
}