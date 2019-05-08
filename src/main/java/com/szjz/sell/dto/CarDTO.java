package com.szjz.sell.dto;

import lombok.Data;

/**
 * @author szjz
 * @date 2019/5/8 18:22
 * 购物车
 */

@Data
public class CarDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CarDTO() {
    }

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
