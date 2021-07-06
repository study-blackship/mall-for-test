package com.mall.order.response;

import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.mapper.OrderMapper;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long shopId;
    private Order.OrderStatus orderStatus;
    private List<OrderEntryResponse> entryResponseList = new ArrayList<>();

    public OrderResponse() {
    }

    @Builder
    public OrderResponse(Long id, Long userId, Long shopId, Order.OrderStatus orderStatus, List<OrderEntry> orderEntryList) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.entryResponseList = orderEntryList.stream().map(OrderMapper.INSTANCE::orderEntryToResponse).collect(Collectors.toList());
    }
}
