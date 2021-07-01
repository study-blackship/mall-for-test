package com.mall.shop.service;

import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.CategoryMapper;
import com.mall.shop.mapper.ShopMapper;
import com.mall.shop.repository.CategoryRepository;
import com.mall.shop.repository.ShopRepository;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.CategoryResponse;
import com.mall.shop.response.ShopResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;

    public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository) {
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
    }

    public ShopResponse registerShop(ShopRequest shopRequest) {
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(categoryRepository.findById(shopRequest.getCategoryId()).orElseThrow());
//        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.categoryToResponse(shop.getCategory());
        return ShopMapper.INSTANCE.shopToResponse(shopRepository.save(shop));
    }

    public Shop updateShop(ShopRequest shopRequest) {
        Shop shop = shopRepository.findById(shopRequest.getId()).orElseThrow();
        shop.update(shopRequest);
        return shop;
    }
}
