package com.mall.shop.response;

import com.mall.base.Money;
import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ShopMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductResponse {
    private Long id;
    private Money price;
    private String label;
    private ShopResponse shopResponse;

    public ProductResponse() {
    }

    @Builder
    public ProductResponse(Long id, Money price, String label, Shop shop) {
        this.id = id;
        this.price = price;
        this.label = label;
        this.shopResponse = ShopMapper.INSTANCE.shopToResponse(shop);
    }
}
