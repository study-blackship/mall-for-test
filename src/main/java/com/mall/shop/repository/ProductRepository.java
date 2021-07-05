package com.mall.shop.repository;

import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQD {
    List<Product> findByShop(Shop shop);
}
