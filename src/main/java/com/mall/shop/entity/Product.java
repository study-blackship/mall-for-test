package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
@Getter
@ToString
public class Product extends Base implements Serializable {
    private Money price;

    private String label;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Shop shop;

    public Product() {
    }

    @Builder
    public Product(Money price, String label, Shop shop) {
        this.price = price;
        this.label = label;
        this.shop = shop;
    }

    public void noMoreShop() {
        this.shop = null;
    }
}
