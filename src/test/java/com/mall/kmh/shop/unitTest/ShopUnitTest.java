package com.mall.kmh.shop.unitTest;

import com.mall.shop.entity.Category;
import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ShopMapper;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.ShopResponse;
import org.junit.jupiter.api.Test;

import static com.mall.kmh.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShopUnitTest {

    @Test
    void ShopRequest로_Shop엔티티_생성() {
        //given
        Category category = aCategory().build();
        ShopRequest shopRequest = aShopRequest().build();

        //when
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(category);

        //then
        assertThat(shop).isNotNull();
        assertThat(shop.getLabel()).isEqualTo(shopRequest.getLabel());
        assertThat(shop.getLocation().getDong()).isEqualTo(shopRequest.getLocation().getDong());
        assertThat(shop.getLocation().getHo()).isEqualTo(shopRequest.getLocation().getHo());
        assertThat(shop.getLocation().getFloor()).isEqualTo(shopRequest.getLocation().getFloor());
        assertThat(shop.getCategory().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    void Shop엔티티로_ShopResponse_생성() {
        //given
        Category category = aCategory().build();
        Shop shop = aShop().build();

        //when
        ShopResponse shopResponse = ShopMapper.INSTANCE.shopToResponse(shop);

        //then
        assertThat(shopResponse).isNotNull();
        assertThat(shopResponse.getLabel()).isEqualTo(shop.getLabel());
        assertThat(shopResponse.getLocation().getDong()).isEqualTo(shop.getLocation().getDong());
        assertThat(shopResponse.getLocation().getHo()).isEqualTo(shop.getLocation().getHo());
        assertThat(shopResponse.getLocation().getFloor()).isEqualTo(shop.getLocation().getFloor());
        assertThat(shopResponse.getCategory().getLabel()).isEqualTo(category.getLabel());
    }
}
