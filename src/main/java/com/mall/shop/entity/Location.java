package com.mall.shop.entity;

import lombok.Builder;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Location {
    enum Dong {A, B, C, D}

    @Enumerated(EnumType.STRING)
    private Dong dong;

    private Integer floor;

    private Integer ho;

    public Location() {
    }

    @Builder
    public Location(Dong dong, Integer floor, Integer ho) {
        this.dong = dong;
        this.floor = floor;
        this.ho = ho;
    }
}
