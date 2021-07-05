package com.mall.shop.request;

import com.mall.base.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private Money price;
    private String label;
    private Long shopId;

    public ProductRequest() {
    }

    @Builder
    public ProductRequest(Long id, Money price, String label, Long shopId) {
        this.id = id;
        this.price = price;
        this.label = label;
        this.shopId = shopId;
    }
}
