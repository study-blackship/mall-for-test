package com.mall.shop.mapper;

import com.mall.shop.entity.Shop;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

    ShopResponse shopToResponse(Shop shop);

    Shop requestToShop(ShopRequest shopRequest);
}
