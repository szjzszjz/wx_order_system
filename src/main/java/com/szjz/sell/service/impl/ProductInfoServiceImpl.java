package com.szjz.sell.service.impl;

import com.szjz.sell.dataobject.ProductInfo;
import com.szjz.sell.dto.CarDTO;
import com.szjz.sell.enums.ProductStatusEnum;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.repository.ProductInfoRepository;
import com.szjz.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author szjz
 * @date 2019/5/8 10:09
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findById(String id) {
        return productInfoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }


    @Override
    public List<ProductInfo> findByProductStatus(Integer integer) {
        return productInfoRepository.findByProductStatus(integer);
    }

    @Override
    public void save(ProductInfo productInfo) {
        productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CarDTO> carDTOList) {
        for (CarDTO carDTO:carDTOList){
            ProductInfo productInfo = productInfoRepository.findById(carDTO.getProductId()).orElse(null);
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(productInfo.getProductStock() + carDTO.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CarDTO> carDTOList) {
        for (CarDTO carDTO:carDTOList){
            ProductInfo productInfo = productInfoRepository.findById(carDTO.getProductId()).orElse(null);
            if (productInfo == null){ //没有该商品
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算剩余的库存
            int number = productInfo.getProductStock() - carDTO.getProductQuantity();
            if (number<0){ //库存不够
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(number);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        //先判断商品是否存在
        if(productInfo == null){
            log.error("【商品下架】 {}",ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //再判断商品的状态
        if(productInfo.getProductStatusEnum().getMessage()=="下架"){
            log.error("【商品下架】 {}",ResultEnum.PRODUCT_STATUS_ERROR.getMessage());
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        //先判断商品是否存在
        if(productInfo == null){
            log.error("【商品上架】 {}",ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //再判断商品的状态
        if(productInfo.getProductStatusEnum().getMessage()=="上架"){
            log.error("【商品上架】 {}",ResultEnum.PRODUCT_STATUS_ERROR.getMessage());
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }
}
