package com.szjz.sell.repository;

import com.szjz.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/8 9:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString());
        productInfo.setProductName("唐诗");
        productInfo.setProductDescription("唐诗300首");
        productInfo.setProductPrice(BigDecimal.valueOf(12.00));
        productInfo.setProductStock(123);
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("http://www.xxx.png");
        ProductInfo info = repository.save(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotNull(list);
    }
}