package com.mall.shop.mapper;

import com.mall.shop.entity.Category;
import com.mall.shop.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryToResponse(Category category);
}
