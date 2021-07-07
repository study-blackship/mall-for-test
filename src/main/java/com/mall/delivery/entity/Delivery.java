package com.mall.delivery.entity;

import com.mall.base.Base;
import com.mall.customer.entity.Address;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DELIVERY")
@Getter
public class Delivery extends Base implements Serializable {
    public enum DeliveryStatus {DELIVERING, DELIVERED}

    private Long orderId;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Address deliveryAddress;

    public Delivery() {
    }

    @Builder
    public Delivery(Long orderId, Long customerId, DeliveryStatus deliveryStatus, Address deliveryAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryStatus = deliveryStatus;
        this.deliveryAddress = deliveryAddress;
    }
}
