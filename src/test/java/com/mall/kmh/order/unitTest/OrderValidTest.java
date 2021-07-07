package com.mall.kmh.order.unitTest;

import com.mall.base.Money;
import com.mall.order.entity.Order;
import com.mall.order.service.OrderValidator;
import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.mall.shop.repository.ProductRepository;
import com.mall.shop.repository.ShopRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.mall.kmh.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class OrderValidTest {
    OrderValidator orderValidator;

    @Test
    void Order등록_성공() {
        //given
        orderValidator = new OrderValidator(mock(ProductRepository.class), mock(ShopRepository.class));

        Product product = aProduct()
                .build();

        Shop shop = aShop()
                .build();

        //when
        Order order = aOrder()
                .build();

        //then
        orderValidator.validate(
                order,
                shop,
                new HashMap<>() {{
                    put(product.getId(), Collections.singletonList(product));
                }}
        );
    }

    @Test
    void 가격이_다른경우_실패() {
        //given
        orderValidator = new OrderValidator(mock(ProductRepository.class), mock(ShopRepository.class));

        Product product = aProduct()
                .price(Money.wons(100_000))
                .build();

        Shop shop = aShop()
                .build();

        //when
        Order order = aOrder()
                .build();

        //then
        assertThrows(RuntimeException.class, () -> orderValidator.validate(
                order,
                shop,
                new HashMap<>() {{
                    put(product.getId(), Collections.singletonList(product));
                }})
        );
    }

    @Test
    void 상품의_점포가_다른경우_실패() {
        //given
        orderValidator = new OrderValidator(mock(ProductRepository.class), mock(ShopRepository.class));

        Product product = aProduct()
                .shop(aShop().id(2L).build())
                .build();

        Shop shop = aShop()
                .build();

        //when
        Order order = aOrder()
                .build();

        //then
        assertThrows(RuntimeException.class, () -> orderValidator.validate(
                order,
                shop,
                new HashMap<>() {{
                    put(product.getId(), Collections.singletonList(product));
                }})
        );
    }

    @Test
    void OrderEntry가_비어있는_경우_실패() {
        //given
        orderValidator = new OrderValidator(mock(ProductRepository.class), mock(ShopRepository.class));

        Product product = aProduct()
                .build();

        Shop shop = aShop()
                .build();

        //when
        Order order = aOrder()
                .orderEntryList(new ArrayList<>())
                .build();

        //then
        assertThrows(RuntimeException.class, () -> orderValidator.validate(
                order,
                shop,
                new HashMap<>() {{
                    put(product.getId(), Collections.singletonList(product));
                }})
        );
    }
}
