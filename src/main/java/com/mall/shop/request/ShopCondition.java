package com.mall.shop.request;

import com.mall.shop.entity.Location;
import lombok.Builder;
import lombok.Data;

@Data
public class ShopCondition {
    private String label;
    private Location.Dong dong;
    private Integer floor;
    private Integer ho;
    private Long categoryId;

    public ShopCondition() {
    }

    @Builder
    public ShopCondition(String label, Location.Dong dong, Integer floor, Integer ho, Long categoryId) {
        this.label = label;
        this.dong = dong;
        this.floor = floor;
        this.ho = ho;
        this.categoryId = categoryId;
    }
}
