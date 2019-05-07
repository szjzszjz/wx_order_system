package com.szjz.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author szjz
 * @date 2019/5/7 15:55
 */
@Entity  //表格对应的实体类 去掉下划线 变为驼峰命名法
//@Table(name = "product_category") 默认生成对应表
//@DynamicUpdate //动态更新  主要用于对修改时间的更新 updateTime
@Data
public class ProductCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    //private Date updateTime;
}
