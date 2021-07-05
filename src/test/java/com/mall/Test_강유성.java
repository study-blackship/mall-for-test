package com.mall;

import com.mall.customer.entity.Address;
import com.mall.customer.entity.Customer;
import com.mall.customer.service.dto.CustomerDto;
import com.mall.customer.service.mapper.CustomerServiceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;

public class Test_강유성 {

    private final CustomerServiceMapper mapper = Mappers.getMapper(CustomerServiceMapper.class);


    @Test
    @DisplayName("MapStruct Test")
    void MapStruct_Test() {
        // given
        Customer source = Customer.builder()
                .name("name")
                .birth(ZonedDateTime.now())
                .address(Address.builder()
                        .city("city")
                        .detail("detail")
                        .street("street")
                        .build())
                .build();
        // when
        CustomerDto dto = mapper.toDto(source);
        Customer customer = mapper.toEntity(dto);
        // then
        assertThat(customer.getName()).isEqualTo(dto.getName());
        assertThat(customer.getAddress().getCity()).isEqualTo(dto.getAddress().getCity());
        assertThat(customer.getAddress().getDetail()).isEqualTo(dto.getAddress().getDetail());
        assertThat(customer.getAddress().getStreet()).isEqualTo(dto.getAddress().getStreet());
        assertThat(customer.getBirth()).isEqualTo(dto.getBirth());

    }
}
