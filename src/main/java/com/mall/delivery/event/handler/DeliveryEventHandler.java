package com.mall.delivery.event.handler;

import com.mall.customer.entity.Customer;
import com.mall.customer.repository.CustomerRepository;
import com.mall.delivery.entity.Delivery;
import com.mall.delivery.repository.DeliveryRepository;
import com.mall.order.event.OrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeliveryEventHandler {
    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;

    public DeliveryEventHandler(DeliveryRepository deliveryRepository, CustomerRepository customerRepository) {
        this.deliveryRepository = deliveryRepository;
        this.customerRepository = customerRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void startDelivery(OrderEvent orderEvent) {
        Customer customer = customerRepository.findById(orderEvent.getOrder().getUserId()).orElseThrow();

        Delivery delivery = Delivery.builder()
                .orderId(orderEvent.getOrder().getId())
                .customerId(customer.getId())
                .deliveryAddress(customer.getAddress())
                .deliveryStatus(Delivery.DeliveryStatus.DELIVERING)
                .build();

        deliveryRepository.save(delivery);
    }
}
