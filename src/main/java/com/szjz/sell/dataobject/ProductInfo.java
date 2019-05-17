package com.szjz.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szjz.sell.enums.ProductStatusEnum;
import com.szjz.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author szjz
 * @date 2019/5/7 19:08
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 3185698047762902158L;
    @Id
    private String productId;

    /** 名称 */
    @Column(nullable = false)
    private String ProductName;

    /**单价*/
    private BigDecimal productPrice;

    /** 库存*/
    private Integer productStock;

    /**描述*/
    private String productDescription;

    /**小图*/
    private String productIcon;

    /**状态 0 上架 1 下架 默认为上架*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    
    /** 类目编号 */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus ,ProductStatusEnum.class);
    }
}
