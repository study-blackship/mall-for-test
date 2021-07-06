package com.mall.shop.mapper;

import com.mall.shop.entity.Product;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product requestToProduct(ProductRequest productRequest);

    ProductResponse productToResponse(Product product);
}
