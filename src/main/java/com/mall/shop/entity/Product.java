package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import com.mall.shop.request.ProductRequest;
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

    public Product(Money price, String label, Shop shop) {
        this.price = price;
        this.label = label;
        this.shop = shop;
    }

    @Builder
    public Product(Long id, Money price, String label, Shop shop) {
        super(id);
        this.price = price;
        this.label = label;
        this.shop = shop;
    }

    public void joinShop(Shop shop) {
        this.shop = shop;
    }

    public void noMoreShop() {
        this.shop = null;
    }

    public void update(ProductRequest productRequest) {
        this.label = productRequest.getLabel();
        this.price = productRequest.getPrice();
    }
}
