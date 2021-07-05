package com.mall.customer.service.mapper;

import com.mall.customer.entity.Customer;
import com.mall.customer.service.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerServiceMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customer);
}
