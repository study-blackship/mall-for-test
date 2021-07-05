package com.mall.kmh.shop.unitTest;

import com.mall.shop.entity.Category;
import com.mall.shop.mapper.CategoryMapper;
import com.mall.shop.request.CategoryRequest;
import com.mall.shop.response.CategoryResponse;
import org.junit.jupiter.api.Test;

import static com.mall.kmh.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryUnitTest {
    @Test
    void CategoryRequest로_Category엔티티_생성() {
        //given
        CategoryRequest categoryRequest = aCategoryRequest().build();

        //when
        Category category = CategoryMapper.INSTANCE.requestToCategory(categoryRequest);

        //then
        assertThat(category).isNotNull();
        assertThat(category.getLabel()).isEqualTo(categoryRequest.getLabel());
    }

    @Test
    void Category엔티티로_CategoryResponse_생성() {
        //given
        Category category = aCategory().build();

        //when
        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.categoryToResponse(category);

        //then
        assertThat(categoryResponse).isNotNull();
        assertThat(categoryResponse.getLabel()).isEqualTo(category.getLabel());
    }
}
