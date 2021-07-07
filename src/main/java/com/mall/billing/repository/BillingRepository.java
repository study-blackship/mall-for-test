package com.mall.billing.repository;

import com.mall.billing.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    public Optional<Billing> findByShopId(Long shopId);
}
