package com.mall.shop.service;

import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ShopMapper;
import com.mall.shop.repository.CategoryRepository;
import com.mall.shop.repository.ShopRepository;
import com.mall.shop.request.ShopRequest;
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

    public Shop registerShop(ShopRequest shopRequest) {
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(categoryRepository.findById(shopRequest.getCategoryId()).orElseThrow());
        return shopRepository.save(shop);
    }

    public Shop updateShop(ShopRequest shopRequest) {
        Shop shop = shopRepository.findById(shopRequest.getId()).orElseThrow();
        shop.update(shopRequest);
        return shop;
    }
}
