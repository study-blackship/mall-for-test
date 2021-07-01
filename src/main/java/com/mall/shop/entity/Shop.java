package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.shop.request.ShopRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SHOP")
@Getter
@ToString
public class Shop extends Base implements Serializable {
    private String label;

    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Shop() {
    }

    @Builder
    public Shop(Long id, String label, Location location, Category category) {
        super(id);
        this.label = label;
        this.location = location;
        this.category = category;
    }

    public void update(ShopRequest shopRequest) {
        this.label = shopRequest.getLabel();
        this.location = shopRequest.getLocation();
    }

    public void joinCategory(Category category) {
        this.category = category;
    }
}
