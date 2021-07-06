package com.mall.order.request;

import com.mall.order.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {
    private Long id;
    private Long userId;
    private Long shopId;
    private Order.OrderStatus orderStatus;
    private List<OrderEntryRequest> entryRequestList = new ArrayList<>();

    public OrderRequest() {
    }

    @Builder
    public OrderRequest(Long id, Long userId, Long shopId, Order.OrderStatus orderStatus, List<OrderEntryRequest> entryRequestList) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.entryRequestList = entryRequestList;
    }
}
