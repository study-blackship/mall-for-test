package com.mall.order.service;

import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.repository.ProductRepository;
import com.mall.shop.repository.ShopRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderValidator {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    public OrderValidator(ProductRepository productRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    public void validate(Order order) {
        validate(order, getShop(order), getProductMap(order));
    }

    public void validate(Order order, Shop shop, Map<Long, List<Product>> productMap) {
        if (order.getOrderEntryList().isEmpty()) throw new RuntimeException("주문 내역이 비어 있습니다.");

        for (OrderEntry orderEntry : order.getOrderEntryList()) {
            for (Product product : productMap.get(orderEntry.getProductId())) {
                if (!product.getShop().getId().equals(shop.getId())) throw new RuntimeException("주문한 점포와 상품의 판매 점포가 일치 하지 않습니다.");
                if (!orderEntry.getPrice().equals(product.getPrice())) throw new RuntimeException("주문한 가격와 상품의 가격이 일치 하지 않습니다.");
            }
        }
    }

    private Shop getShop(Order order) {
        return shopRepository.findById(order.getShopId()).orElseThrow();
    }

    private Map<Long, List<Product>> getProductMap(Order order) {
        return productRepository.findAllById(order.getOrderEntryList().stream().map(OrderEntry::getProductId).collect(Collectors.toList()))
                .stream().collect(Collectors.groupingBy(Product::getId));
    }
}
