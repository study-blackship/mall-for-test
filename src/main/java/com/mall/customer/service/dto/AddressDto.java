package com.mall.customer.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
public class AddressDto {

    private String city;

    private String street;

    private String detail;

}
