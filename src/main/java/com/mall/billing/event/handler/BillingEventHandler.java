package com.mall.billing.event.handler;

import com.mall.base.Money;
import com.mall.billing.entity.Billing;
import com.mall.billing.repository.BillingRepository;
import com.mall.order.event.OrderDeliveredEvent;
import com.mall.shop.entity.Shop;
import com.mall.shop.repository.ShopRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BillingEventHandler {
    private final ShopRepository shopRepository;
    private final BillingRepository billingRepository;

    public BillingEventHandler(ShopRepository shopRepository, BillingRepository billingRepository) {
        this.shopRepository = shopRepository;
        this.billingRepository = billingRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void calculateBilling(OrderDeliveredEvent orderDeliveredEvent) {
        Shop shop = shopRepository.findById(orderDeliveredEvent.getOrder().getShopId()).orElseThrow();

        Billing billing = billingRepository.findByShopId(shop.getId())
                .orElse(billingRepository.save(new Billing(shop.getId(), Money.ZERO)));

        billing.billCommissionFee(shop.calculateCommissionFee(orderDeliveredEvent.getOrder().calculateTotalPrice()));
    }
}
