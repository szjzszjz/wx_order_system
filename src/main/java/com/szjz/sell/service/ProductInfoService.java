package com.szjz.sell.service;

import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 10:08
 */
public interface ProductInfoService {

    ProductInfo findById(String id);

    /** 分页查询所有的上架商品 */
    List<ProductInfo> findUpAll();

    /** 查询所有分页查询 */
    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer integer);

    void save(ProductInfo productInfo);

    /** 加库存 */
    void increaseStock(List<CarDTO> carDTOList);

    /** 减库存 */
    void decreaseStock(List<CarDTO> carDTOList);
}
