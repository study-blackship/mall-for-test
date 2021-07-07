package com.mall.shop.request;

import com.mall.base.Ratio;
import com.mall.shop.entity.Location;
import lombok.Builder;
import lombok.Data;

@Data
public class ShopRequest {
    private Long id;
    private String label;
    private Location location;
    private Long categoryId;
    private Ratio commissionRate;

    public ShopRequest() {
    }

    public ShopRequest(String label, Location location, Long categoryId, Ratio commissionRate) {
        this.label = label;
        this.location = location;
        this.categoryId = categoryId;
        this.commissionRate = commissionRate;
    }

    @Builder
    public ShopRequest(Long id, String label, Location location, Long categoryId, Ratio commissionRate) {
        this.id = id;
        this.label = label;
        this.location = location;
        this.categoryId = categoryId;
        this.commissionRate = commissionRate;
    }
}
