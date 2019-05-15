package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.enums.ProductStatusEnum;
import com.szjz.sell.repository.ProductInfoRepository;
import com.szjz.sell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/8 10:16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findById() {
        ProductInfo productInfo = repository.findById("1232").orElse(null);
        System.out.println(productInfo);

    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<ProductInfo> pageInfo = repository.findAll(pageRequest);
        System.out.println(pageInfo.getTotalElements());
        System.out.println(pageInfo.getTotalPages());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(ProductStatusEnum.UP.getCode());
        System.out.println(list);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111");
        productInfo.setProductName("宋词");
        productInfo.setProductDescription("宋词300首");
        productInfo.setProductPrice(BigDecimal.valueOf(12.00));
        productInfo.setProductStock(123);
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("http://www.xxx.png");
        ProductInfo info = repository.save(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = repository.findById("123").orElse(null);
        productInfo = productInfoService.onSale(productInfo.getProductId());
        System.err.println(productInfo);
    }

    @Test
    public void offSale(){
        ProductInfo productInfo = repository.findById("123").orElse(null);
        productInfo = productInfoService.offSale(productInfo.getProductId());
        System.err.println(productInfo);
    }

}