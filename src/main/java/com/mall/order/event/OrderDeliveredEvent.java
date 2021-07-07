package com.mall.order.event;

import com.mall.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderDeliveredEvent {
    private final Order order;

    public OrderDeliveredEvent(Order order) {
        this.order = order;
    }
}
