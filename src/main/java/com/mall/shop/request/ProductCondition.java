package com.mall.shop.request;

import com.mall.base.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductCondition {
    private String label;
    private Long shopId;

    public ProductCondition() {
    }

    @Builder
    public ProductCondition(String label, Long shopId) {
        this.label = label;
        this.shopId = shopId;
    }
}
