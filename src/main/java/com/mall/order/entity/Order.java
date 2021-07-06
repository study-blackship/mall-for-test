package com.mall.order.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import com.mall.order.service.OrderValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@ToString
@Getter
public class Order extends Base implements Serializable {
    public enum OrderStatus {ORDERED, PAYED, DELIVERED}

    private Long userId;

    private Long shopId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderEntry> orderEntryList = new ArrayList<>();

    public Order() {
    }

    @Builder
    public Order(Long id, Long userId, Long shopId, OrderStatus orderStatus, List<OrderEntry> orderEntryList) {
        super(id);
        this.userId = userId;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.orderEntryList = orderEntryList;
    }

    public void addOrderEntry(OrderEntry orderEntry) {
        checkOrderEntryListIsNull();
        this.orderEntryList.add(orderEntry);
        orderEntry.mapToOrder(this);
    }

    public void addOrderEntry(List<OrderEntry> orderEntries) {
        checkOrderEntryListIsNull();
        this.orderEntryList.addAll(orderEntries);
        orderEntries.forEach(orderEntry -> orderEntry.mapToOrder(this));
    }

    private void checkOrderEntryListIsNull() {
        if (this.orderEntryList == null) this.orderEntryList = new ArrayList<>();
    }

    public Money calculateTotalPrice() {
        return Money.sum(orderEntryList, OrderEntry::getPrice);
    }

    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
    }
}
