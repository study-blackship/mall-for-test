package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.base.Money;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product extends Base {
    private Money price;

    private String label;

    @ManyToOne
    private Shop shop;
}
