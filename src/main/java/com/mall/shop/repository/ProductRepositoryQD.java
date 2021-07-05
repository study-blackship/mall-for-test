package com.mall.shop.repository;

import com.mall.shop.entity.Product;
import com.mall.shop.request.ProductCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQD {
    Page<Product> selectByCondition(ProductCondition productCondition, Pageable pageable);
}
