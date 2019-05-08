package com.szjz.sell.repository;

import com.szjz.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 9:20
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /** 通过商品的状态查询商品 */
    List<ProductInfo> findByProductStatus(Integer integer);
}
