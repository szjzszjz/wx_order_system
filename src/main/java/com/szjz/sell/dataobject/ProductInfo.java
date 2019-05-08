package com.szjz.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author szjz
 * @date 2019/5/7 19:08
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    /** 名称 */
    private String ProductName;

    /**单价*/
    private BigDecimal productPrice;

    /** 库存*/
    private Integer productStock;

    /**描述*/
    private String productDescription;

    /**小图*/
    private String productIcon;

    /**状态 0正常 1 下架*/
    private Integer productStatus;
    
    /** 类目编号 */
    private Integer categoryType;



}
