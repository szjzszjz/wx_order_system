package com.szjz.sell.service;

import com.szjz.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/7 18:30
 */
public interface ProductCategoryService {

    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory save(ProductCategory pc);

}
