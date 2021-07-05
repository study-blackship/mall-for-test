package com.mall.customer.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
public class CustomerDto {

    private Long id;

    private ZonedDateTime createdAt;

    private String name;

    private ZonedDateTime birth;

    private AddressDto address;
}
