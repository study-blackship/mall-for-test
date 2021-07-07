package com.mall.shop.response;

import com.mall.base.Ratio;
import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.mapper.CategoryMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopResponse {
    private Long id;
    private String label;
    private Location location;
    private CategoryResponse category;
    private Ratio commissionRate;

    public ShopResponse() {
    }

    @Builder
    public ShopResponse(Long id, String label, Location location, Category category, Ratio commissionRate) {
        this.id = id;
        this.label = label;
        this.location = location;
        this.category = CategoryMapper.INSTANCE.categoryToResponse(category);
        this.commissionRate = commissionRate;
    }
}
