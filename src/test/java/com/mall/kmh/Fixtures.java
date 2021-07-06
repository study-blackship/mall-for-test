package com.mall.kmh;

import com.mall.base.Money;
import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.request.OrderEntryRequest;
import com.mall.order.request.OrderRequest;
import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.request.CategoryRequest;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.request.ShopRequest;

import java.util.Collections;

public class Fixtures {
    public static Category.CategoryBuilder aCategory() {
        return Category.builder()
                .id(1L)
                .label("침구류");
    }

    public static CategoryRequest.CategoryRequestBuilder aCategoryRequest() {
        return CategoryRequest.builder()
                .label("침구류");
    }

    public static Shop.ShopBuilder aShop() {
        Category category = aCategory().build();
        return Shop.builder()
                .id(1L)
                .label("이케아")
                .category(category)
                .location(Location.builder()
                        .dong(Location.Dong.A)
                        .floor(1)
                        .ho(104)
                        .build());
    }

    public static ShopRequest.ShopRequestBuilder aShopRequest() {
        Category category = aCategory().build();
        return ShopRequest.builder()
                .id(1L)
                .label("이케아")
                .location(Location.builder()
                        .dong(Location.Dong.A)
                        .floor(1)
                        .ho(104)
                        .build())
                .categoryId(category.getId());
    }

    public static Product.ProductBuilder aProduct() {
        return Product.builder()
                .id(1L)
                .label("2층침대")
                .shop(aShop().build())
                .price(Money.wons(300_000_000));
    }

    public static ProductRequest.ProductRequestBuilder aProductRequest() {
        return ProductRequest.builder()
                .label("2층침대")
                .price(Money.wons(300_000_000));
    }

    public static OrderRequest.OrderRequestBuilder aOrderRequest() {
        return OrderRequest.builder()
                .id(1L)
                .userId(1L)
                .shopId(aShop().build().getId())
                .orderStatus(Order.OrderStatus.ORDERED)
                .entryRequestList(Collections.singletonList(aOrderEntryRequest().build()));
    }

    public static OrderEntryRequest.OrderEntryRequestBuilder aOrderEntryRequest() {
        Product product = aProduct().build();
        return OrderEntryRequest.builder()
                .id(1L)
                .count(3)
                .productId(product.getId())
                .price(product.getPrice());

    }

    public static Order.OrderBuilder aOrder() {
        return Order.builder()
                .id(1L)
                .userId(1L)
                .shopId(aShop().build().getId())
                .orderStatus(Order.OrderStatus.ORDERED)
                .orderEntryList(Collections.singletonList(aOrderEntry().build()));
    }

    public static OrderEntry.OrderEntryBuilder aOrderEntry() {
        Product product = aProduct().build();
        return OrderEntry.builder()
                .id(1L)
                .count(3)
                .productId(product.getId())
                .price(product.getPrice());
    }
}
