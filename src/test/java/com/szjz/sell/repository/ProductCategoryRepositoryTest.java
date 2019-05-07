package com.szjz.sell.repository;

import com.szjz.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/7 16:05
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
//    @Transactional //测试数据完全回滚，不污染数据库
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("图书馆");
        productCategory.setCategoryType(7);
        repository.save(productCategory);
    }

    @Test
    public void findOneTest(){
        List<ProductCategory> all = repository.findAll();
        System.out.println(all);
        Optional<ProductCategory> byId = repository.findById(1);
        System.out.println(byId);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("舞0989");
        productCategory.setCategoryType(8);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<ProductCategory> list = repository.findByCategoryTypeIn(integers);
        System.out.println(list);
        Assert.assertNotEquals(0,list.size());
    }
}