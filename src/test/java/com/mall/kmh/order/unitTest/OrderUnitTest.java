package com.mall.kmh.order.unitTest;

import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.request.OrderEntryRequest;
import com.mall.order.request.OrderRequest;
import com.mall.order.response.OrderEntryResponse;
import com.mall.order.response.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.mall.kmh.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderUnitTest {


    @Test
    void OrderEntryRequest로_OrderEntry엔티티_생성() {
        //given
        OrderEntryRequest orderEntryRequest = aOrderEntryRequest().build();

        //when
        OrderEntry orderEntry = OrderMapper.INSTANCE.requestToOrderEntry(orderEntryRequest);

        //then
        assertThat(orderEntry).isNotNull();
        assertThat(orderEntry.getCount()).isEqualTo(orderEntryRequest.getCount());
        assertThat(orderEntry.getPrice()).isEqualTo(orderEntryRequest.getPrice());
        assertThat(orderEntry.getProductId()).isEqualTo(orderEntryRequest.getProductId());
    }

    @Test
    void OrderRequest로_Order엔티티_생성() {
        //given
        OrderRequest orderRequest = aOrderRequest().build();

        //when
        Order order = OrderMapper.INSTANCE.requestToOrder(orderRequest);
        order.addOrderEntry(orderRequest.getEntryRequestList().stream().map(OrderMapper.INSTANCE::requestToOrderEntry).collect(Collectors.toList()));

        //then
        assertThat(order).isNotNull();
        assertThat(order.getOrderStatus()).isEqualTo(orderRequest.getOrderStatus());
        assertThat(order.getShopId()).isEqualTo(orderRequest.getShopId());
        assertThat(order.getUserId()).isEqualTo(orderRequest.getUserId());
        assertThat(order.getOrderEntryList()).isNotNull();
    }

    @Test
    void OrderEntry엔티티로_OrderEntryResponse_생성() {
        //given
        OrderEntry orderEntry = aOrderEntry().build();

        //when
        OrderEntryResponse orderEntryResponse = OrderMapper.INSTANCE.orderEntryToResponse(orderEntry);

        //then
        assertThat(orderEntryResponse).isNotNull();
        assertThat(orderEntryResponse.getCount()).isEqualTo(orderEntry.getCount());
        assertThat(orderEntryResponse.getPrice()).isEqualTo(orderEntry.getPrice());
        assertThat(orderEntryResponse.getProductId()).isEqualTo(orderEntry.getProductId());
    }

    @Test
    void Order엔티티로_OrderResponse_생성() {
        //given
        Order order = aOrder().build();

        //when
        OrderResponse orderResponse = OrderMapper.INSTANCE.orderToResponse(order);

        //then
        assertThat(orderResponse).isNotNull();
        assertThat(orderResponse.getOrderStatus()).isEqualTo(order.getOrderStatus());
        assertThat(orderResponse.getShopId()).isEqualTo(order.getShopId());
        assertThat(orderResponse.getUserId()).isEqualTo(order.getUserId());
        assertThat(orderResponse.getEntryResponseList()).isNotNull();
    }


}
