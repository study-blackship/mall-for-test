package com.mall.shop.entity;

import com.mall.base.Base;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SHOP")
public class Shop extends Base implements Serializable {
    private String label;

    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Shop() {
    }

    @Builder
    public Shop(String label, Location location, Category category) {
        this.label = label;
        this.location = location;
        this.category = category;
    }
}
