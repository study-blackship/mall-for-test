package com.mall.order.request;

import com.mall.base.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderEntryRequest {
    private Long id;
    private Long productId;
    private Integer count;
    private Money price;

    public OrderEntryRequest() {
    }

    @Builder
    public OrderEntryRequest(Long id, Long productId, Integer count, Money price) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.price = price;
    }
}
