package com.mall.shop.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryResponse {
    private Long id;
    private String label;

    public CategoryResponse() {
    }

    @Builder
    public CategoryResponse(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
