package com.mall.order.repository;

import com.mall.order.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
