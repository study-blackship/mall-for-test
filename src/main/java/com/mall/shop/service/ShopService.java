package com.mall.shop.service;

import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ShopMapper;
import com.mall.shop.repository.CategoryRepository;
import com.mall.shop.repository.ProductRepository;
import com.mall.shop.repository.ShopRepository;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.ShopResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ShopResponse registerShop(ShopRequest shopRequest) {
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(categoryRepository.findById(shopRequest.getCategoryId()).orElseThrow());
        return ShopMapper.INSTANCE.shopToResponse(shopRepository.save(shop));
    }

    @Transactional
    public ShopResponse updateShop(ShopRequest shopRequest) {
        Shop shop = shopRepository.findById(shopRequest.getId()).orElseThrow();
        shop.update(shopRequest);
        return ShopMapper.INSTANCE.shopToResponse(shop);
    }

    @Transactional
    public void deleteShop(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow();
        productRepository.findByShop(shop).forEach(Product::noMoreShop);
        shopRepository.deleteById(shop.getId());
    }
}
