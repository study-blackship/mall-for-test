package com.mall.order.response;

import com.mall.base.Money;
import com.mall.order.entity.Order;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderEntryResponse {
    private Long id;
    private Long productId;
    private Integer count;
    private Money price;

    public OrderEntryResponse() {
    }

    @Builder
    public OrderEntryResponse(Long id, Long productId, Integer count, Money price) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.price = price;
    }
}
