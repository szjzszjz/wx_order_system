package com.szjz.sell.controller;

import com.szjz.sell.dataobject.ProductCategory;
import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.service.ProductCategoryService;
import com.szjz.sell.service.ProductInfoService;
import com.szjz.sell.utils.KeyUtil;
import com.szjz.sell.utils.ResultObjectUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author szjz
 * @date 2019/5/15 9:15
 * 卖家商品
 */

@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    @ApiOperation(value = "商品列表")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", pageNum);
        map.put("pageSize", pageSize);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping(value = "/offSale", method = RequestMethod.GET)
    @ApiOperation(value = "下架")
    public ModelAndView offSale(@RequestParam String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping(value = "/onSale", method = RequestMethod.GET)
    @ApiOperation(value = "上架")
    public ModelAndView onSale(@RequestParam String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
            map.put("title", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "展示商品详情/填写新增的商品信息", notes = "有productId 展示商品信息，无productId 填写商品信息")
    public ModelAndView index(@RequestParam(required = false) String productId) {
        Map<String, Object> map = new HashMap<>();
        if (productId != null) {
            ProductInfo productInfo = productInfoService.findById(productId);
            map.put("productInfo", productInfo);
        }

        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product/index", map);
    }

    //    一般校验放在前端
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "新增/修改商品", notes = "有productId为修改商品，无productId为添加新的商品", response = ResultObject.class)
    public ModelAndView save(
            @RequestParam String productId,
            @RequestParam String productName,
            @RequestParam Integer productPrice,
            @RequestParam Integer productStock,
            @RequestParam String productDescription,
            @RequestParam String productIcon,
            @RequestParam Integer categoryType
    ) {

        ProductInfo productInfo = new ProductInfo();
        Map<String, Object> map = new HashMap<>();
        //添加商品
        if (productId == "") {
            productInfo.setProductId(KeyUtil.genUniqueKey());
            log.info("【添加商品】 商品编号={}", productInfo.getProductId());
            map.put("msg", "添加商品成功");
        }
        //修改商品
        else {
            map.put("msg", "修改商品成功");
            productInfo = productInfoService.findById(productId);
        }

        productInfo.setProductName(productName);
        productInfo.setProductPrice(new BigDecimal(productPrice));
        productInfo.setProductStock(productStock);
        productInfo.setProductDescription(productDescription);
        productInfo.setProductIcon(productIcon);
        productInfo.setCategoryType(categoryType);

        ProductInfo save = productInfoService.save(productInfo);
        log.info("save= {}",save);
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
