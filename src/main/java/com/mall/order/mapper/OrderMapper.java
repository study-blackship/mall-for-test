package com.mall.order.mapper;

import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.request.OrderEntryRequest;
import com.mall.order.request.OrderRequest;
import com.mall.order.response.OrderEntryResponse;
import com.mall.order.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order requestToOrder(OrderRequest orderRequest);
    OrderEntry requestToOrderEntry(OrderEntryRequest orderEntryRequest);

    OrderResponse orderToResponse(Order order);
    OrderEntryResponse orderEntryToResponse(OrderEntry orderEntry);
}
