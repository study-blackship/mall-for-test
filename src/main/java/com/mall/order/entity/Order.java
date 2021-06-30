package com.mall.order.entity;

import com.mall.base.Base;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ORDERS")
public class Order extends Base implements Serializable {
    enum OrderStatus {ORDERED, PAYED, DELIVERED}

    private Long userId;

    private Long shopId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    @Builder
    public Order(Long userId, Long shopId, OrderStatus orderStatus) {
        this.userId = userId;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
    }
}
