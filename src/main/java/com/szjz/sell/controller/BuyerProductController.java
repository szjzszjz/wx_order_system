package com.szjz.sell.controller;

import com.szjz.sell.dataobject.ProductCategory;
import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.resultObject.CategoryObject;
import com.szjz.sell.resultObject.ProductObject;
import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.service.ProductCategoryService;
import com.szjz.sell.service.ProductInfoService;
import com.szjz.sell.utils.ResultObjectUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author szjz
 * @date 2019/5/8 11:49
 * 用户购买商品
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询所有商品", notes = "", response = ResultObject.class)
    public ResultObject getList() {

        /** 查询所有的商品 */
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        /** 获取类目的第一种方法 */
       /* List<Integer> cTypeList = new ArrayList<>();
        for (ProductInfo productInfo: productInfoList) {
            cTypeList.add(productInfo.getCategoryType());
        }*/

        /** 第二种方法：lambda 表达式 */
        List<Integer> cTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        System.err.println("cTypeList:" + cTypeList);

        /** 获取所有的产品分类 */
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(cTypeList);
        System.err.println("productCategoryList:" + productCategoryList);

        List<CategoryObject> categoryObjectList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            CategoryObject categoryObject = new CategoryObject();
            categoryObject.setCategoryName(productCategory.getCategoryName());
            categoryObject.setCategoryType(productCategory.getCategoryType());
            categoryObjectList.add(categoryObject);

            List<ProductObject> productObjectList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (categoryObject.getCategoryType().equals(productInfo.getCategoryType())) {

                    ProductObject productObject = new ProductObject();
                    BeanUtils.copyProperties(productInfo, productObject); //属性名称一定要对应
//                    productObject.setProductDescription(productInfo.getProductDescription());
//                    productObject.setProductIcon(productInfo.getProductIcon());
//                    productObject.setProductName(productInfo.getProductName());
//                    productObject.setProductPrice(productInfo.getProductPrice());
                    productObjectList.add(productObject);
                }
            }
            categoryObject.setProductList(productObjectList);
        }

        return ResultObjectUtil.success(categoryObjectList);
    }

}
