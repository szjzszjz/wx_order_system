package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.ProductCategory;
import com.szjz.sell.repository.ProductCategoryRepository;
import com.szjz.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/7 18:36
 */

@Service
public class ProductCategoryServiceImpl  implements ProductCategoryService{

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findById(Integer categoryId) {
        ProductCategory productCategory = repository.findById(categoryId).orElse(null);
        return productCategory;
    }
    //分页查询
    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return  repository.findAll(pageable);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return repository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory pc) {
        return repository.save(pc);
    }
}
