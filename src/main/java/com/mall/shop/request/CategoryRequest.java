package com.mall.shop.request;

import lombok.Builder;
import lombok.Data;

@Data
public class CategoryRequest {
    private Long id;
    private String label;

    public CategoryRequest() {
    }

    @Builder
    public CategoryRequest(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
