package com.szjz.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.lly835.bestpay.rest.type.Post;
import com.szjz.sell.dataobject.ProductCategory;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.service.ProductCategoryService;
import com.szjz.sell.utils.KeyUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author szjz
 * @date 2019/5/15 17:14
 * 卖家商品类目
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @ApiOperation(value = "商品类目列表")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<ProductCategory> productCategoryPage = productCategoryService.findAll(pageRequest);
        map.put("productCategoryPage", productCategoryPage);
        map.put("currentPage", pageNum);
        map.put("pageSize", pageSize);
        return new ModelAndView("category/list", map);
    }

    @GetMapping(value = "/index")
    @ApiOperation(value = "展示类目的详情或者添加类目")
    public ModelAndView index(@RequestParam(required = false) Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        if (categoryId != null) {
            ProductCategory productCategory = productCategoryService.findById(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存/更新类目")
    public ModelAndView saveOrUpdate(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam String categoryName,
                                     @RequestParam Integer categoryType) {
        Map<String, Object> map = new HashMap<>();
        ProductCategory productCategory = new ProductCategory();

        if (categoryId == null) {
            //id自增 由数据库管理
            map.put("msg", "添加类目成功");
        } else {
            productCategory = productCategoryService.findById(categoryId);
            map.put("msg", "修改类目成功");
        }
        productCategory.setCategoryName(categoryName);
        productCategory.setCategoryType(categoryType);

//        因为categoryType作为索引具有唯一性，所有修改的时候可能会重复，此时保存数据就会出异常
        try {
            productCategoryService.save(productCategory);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【保存/更新类目】 {}", ResultEnum.INDEX_IS_REPEAT.getMessage());
            map.put("msg", ResultEnum.INDEX_IS_REPEAT.getMessage() + "," + "修改失败！");
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
