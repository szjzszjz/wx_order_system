package com.szjz.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author szjz
 * @date 2019/5/9 15:39
 * 表单验证
 */

@Data
public class OrderForm {

    /** 买家姓名 */
    @NotEmpty(message = "名字必填")
    private String buyerName;

    /** 买家手机号 */
    @NotEmpty(message = "手机号必填")
    private String buyerPhone;

    /** 买家地址 */
    @NotEmpty(message = "地址必填")
    private String buyerAddress;
    
    /** 买家微信openid */
    @NotEmpty(message = "openid必填")
    private String buyerOpenid;

    /** 购物车 */
    @NotEmpty(message = "购物车不能为空")
    private String items;

    /** 商品id */
   // @NotEmpty(message = "产品id不能为空")
    private String productId;

    /** 购买的商品数量 */
  //  @NotEmpty(message = "购买商品数量必须大于0")
    private Integer productQuantity;
}
