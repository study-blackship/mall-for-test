package com.mall.kmh.shop.unitTest;

import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ProductMapper;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.response.ProductResponse;
import org.junit.jupiter.api.Test;

import static com.mall.kmh.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductUnitTest {

    @Test
    void ProductRequest로_Product엔티티_생성성() {
        //given
        Shop shop = aShop().build();
        ProductRequest productRequest = aProductRequest().build();

        //when
        Product product = ProductMapper.INSTANCE.requestToProduct(productRequest);
        product.joinShop(shop);

        //then
        assertThat(product).isNotNull();
        assertThat(product.getLabel()).isEqualTo(productRequest.getLabel());
        assertThat(product.getPrice()).isEqualTo(product.getPrice());
        assertThat(product.getShop()).isNotNull();
        assertThat(product.getShop().getId()).isEqualTo(shop.getId());
    }

    @Test
    void Product엔티티로_ProductResponse_생성() {
        //given
        Product product = aProduct().build();
        product.joinShop(aShop().build());

        //when
        ProductResponse productResponse = ProductMapper.INSTANCE.productToResponse(product);

        //then
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getLabel()).isEqualTo(product.getLabel());
        assertThat(productResponse.getPrice()).isEqualTo(product.getPrice());
        assertThat(productResponse.getShopResponse()).isNotNull();
        assertThat(productResponse.getShopResponse().getId()).isEqualTo(product.getShop().getId());
        assertThat(productResponse.getShopResponse().getLabel()).isEqualTo(product.getShop().getLabel());
        assertThat(productResponse.getShopResponse().getCategory().getLabel()).isEqualTo(product.getShop().getCategory().getLabel());
    }
}
