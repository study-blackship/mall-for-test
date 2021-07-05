package com.mall.shop.service;

import com.mall.base.PageRequest;
import com.mall.shop.entity.Product;
import com.mall.shop.mapper.ProductMapper;
import com.mall.shop.repository.ProductRepository;
import com.mall.shop.repository.ShopRepository;
import com.mall.shop.request.ProductCondition;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    public ProductService(ProductRepository productRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    public Page<ProductResponse> selectByCondition(ProductCondition productCondition, PageRequest pageRequest) {
        return productRepository.selectByCondition(productCondition, pageRequest.of()).map(ProductMapper.INSTANCE::productToResponse);
    }

    public ProductResponse registerProduct(ProductRequest productRequest) {
        Product product = ProductMapper.INSTANCE.requestToProduct(productRequest);
        product.joinShop(shopRepository.findById(productRequest.getShopId()).orElseThrow());
        return ProductMapper.INSTANCE.productToResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getId()).orElseThrow();
        product.update(productRequest);
        return ProductMapper.INSTANCE.productToResponse(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
