package com.mall.base;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@ToString
abstract public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final ZonedDateTime createdAt = ZonedDateTime.now();

    public Base() {
    }

    public Base(Long id) {
        this.id = id;
    }
}
