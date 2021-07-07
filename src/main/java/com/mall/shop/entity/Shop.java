package com.mall.shop.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import com.mall.base.Ratio;
import com.mall.shop.request.ShopRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SHOP")
@Getter
@ToString
public class Shop extends Base implements Serializable {
    private String label;

    @Embedded
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Ratio commissionRate;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Shop() {
    }

    public Shop(String label, Location location, Category category, Ratio commissionRate) {
        this.label = label;
        this.location = location;
        this.category = category;
        this.commissionRate = commissionRate;
    }

    @Builder
    public Shop(Long id, String label, Location location, Category category, Ratio commissionRate) {
        super(id);
        this.label = label;
        this.location = location;
        this.category = category;
        this.commissionRate = commissionRate;
    }

    public void update(ShopRequest shopRequest) {
        this.label = shopRequest.getLabel();
        this.location = shopRequest.getLocation();
    }

    public void joinCategory(Category category) {
        this.category = category;
    }

    public Money calculateCommissionFee(Money money) {
        return commissionRate.of(money);
    }
}
