package com.mall.customer.entity;

import lombok.Builder;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;

    private String street;

    private String detail;

    public Address() {
    }

    @Builder
    public Address(String city, String street, String detail) {
        this.city = city;
        this.street = street;
        this.detail = detail;
    }
}
