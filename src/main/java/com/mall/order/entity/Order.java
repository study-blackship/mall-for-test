package com.mall.order.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends Base implements Serializable {
    enum OrderStatus {ORDERED, PAYED, DELIVERED}

    private Long userId;

    private Long shopId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderEntry> orderEntryList = new ArrayList<>();

    public Order() {
    }

    @Builder
    public Order(Long userId, Long shopId, OrderStatus orderStatus, List<OrderEntry> orderEntryList) {
        this.userId = userId;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.orderEntryList = orderEntryList;
    }

    public void addOrderEntry(OrderEntry orderEntry) {
        this.orderEntryList.add(orderEntry);
        orderEntry.mapToOrder(this);
    }

    public Money calculateTotalPrice() {
        return Money.sum(orderEntryList, OrderEntry::getPrice);
    }
}
