package com.mall.order.service;

import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.repository.OrderEntryRepository;
import com.mall.order.repository.OrderRepository;
import com.mall.order.request.OrderRequest;
import com.mall.order.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEntryRepository orderEntryRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderRepository orderRepository, OrderEntryRepository orderEntryRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderEntryRepository = orderEntryRepository;
        this.orderValidator = orderValidator;
    }

    public OrderResponse registerOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.INSTANCE.requestToOrder(orderRequest);
        List<OrderEntry> orderEntries = orderRequest.getEntryRequestList().stream().map(OrderMapper.INSTANCE::requestToOrderEntry).collect(Collectors.toList());
        order.addOrderEntry(orderEntries);
        order.place(orderValidator);
        return OrderMapper.INSTANCE.orderToResponse(orderRepository.save(order));
    }
}
