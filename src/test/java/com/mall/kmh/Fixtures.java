package com.mall.kmh;

import com.mall.base.Money;
import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.request.CategoryRequest;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.request.ShopRequest;

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
                .price(Money.wons(300_000_000));
    }

    public static ProductRequest.ProductRequestBuilder aProductRequest() {
        return ProductRequest.builder()
                .label("2층침대")
                .price(Money.wons(300_000_000));
    }
}
