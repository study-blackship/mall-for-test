package com.mall.shop.request;

import com.mall.shop.entity.Location;
import lombok.Data;

@Data
public class ShopRequest {
    private Long id;
    private String label;
    private Location location;
    private Long categoryId;

    public ShopRequest() {
    }

    public ShopRequest(String label, Location location, Long categoryId) {
        this.label = label;
        this.location = location;
        this.categoryId = categoryId;
    }

    public ShopRequest(Long id, String label, Location location, Long categoryId) {
        this.id = id;
        this.label = label;
        this.location = location;
        this.categoryId = categoryId;
    }
}
