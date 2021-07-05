package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
@Getter
public class Product extends Base {
    private Money price;

    private String label;

    @ManyToOne
    private Shop shop;

    public Product() {
    }

    @Builder
    public Product(Money price, String label, Shop shop) {
        this.price = price;
        this.label = label;
        this.shop = shop;
    }
}
