package com.mall.shop.repository;

import com.mall.shop.entity.Shop;
import com.mall.shop.request.ShopCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopRepositoryQD {
    Page<Shop> selectByCondition(ShopCondition shopCondition, Pageable pageable);
}
