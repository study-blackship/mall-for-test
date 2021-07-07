package com.mall.order.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ENTRY")
@Getter
@ToString(exclude = "order")
public class OrderEntry extends Base implements Serializable {
    @ManyToOne
    private Order order;

    private Long productId;

    private Integer count;

    private Money price;

    public OrderEntry() {
    }

    @Builder
    public OrderEntry(Long id, Order order, Long productId, Integer count, Money price) {
        super(id);
        this.order = order;
        this.productId = productId;
        this.count = count;
        this.price = price;
    }

    public void mapToOrder(Order order) {
        this.order = order;
    }

    public Money calculatePrice() {
        return price.times(count);
    }
}
