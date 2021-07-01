package com.mall.shop.mapper;

import com.mall.shop.entity.Shop;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.CategoryResponse;
import com.mall.shop.response.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

//    @Mapping(target = "category", source = "categoryResponse")
    ShopResponse shopToResponse(Shop shop);

    Shop requestToShop(ShopRequest shopRequest);
}
