package com.mall.shop.request;

import com.mall.base.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductCondition {
    private String label;
    private Long shopId;
    private Money min;
    private Money max;

    public ProductCondition() {
    }

    @Builder
    public ProductCondition(String label, Long shopId, Money min, Money max) {
        this.label = label;
        this.shopId = shopId;
        this.min = min;
        this.max = max;
    }
}
