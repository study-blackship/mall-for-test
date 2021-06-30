package com.mall.customer.entity;

import com.mall.base.Base;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Getter
@Table(name = "CUSTOMER")
public class Customer extends Base {
    private String name;

    private ZonedDateTime birth;

    private Address address;

    public Customer() {
    }

    @Builder
    public Customer(String name, ZonedDateTime birth, Address address) {
        this.name = name;
        this.birth = birth;
        this.address = address;
    }
}
