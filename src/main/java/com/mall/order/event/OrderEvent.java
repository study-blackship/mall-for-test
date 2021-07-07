package com.mall.order.event;

import com.mall.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderEvent {
    private final Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }
}
