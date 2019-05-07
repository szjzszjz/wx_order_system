package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author szjz
 * @date 2019/5/7 18:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
   private ProductCategoryServiceImpl pci;

    @Test
    public void findById() {
        ProductCategory pciById = pci.findById(2);
        //ssert.assertEquals(new Integer(2),pciById);
        System.out.println(pciById);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = pci.findAll();
        Assert.assertNotNull(all);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = pci.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
        Assert.assertNotNull(list);
    }

    @Test
    public void save() {
        ProductCategory pc = new ProductCategory("男生庄勇", 5);
        Assert.assertNotNull(pc);
    }
}