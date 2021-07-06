package com.mall.kys.customer.service;

import com.mall.customer.entity.Address;
import com.mall.customer.entity.Customer;
import com.mall.customer.repository.CustomerRepository;
import com.mall.customer.service.CustomerService;
import com.mall.customer.service.dto.AddressDto;
import com.mall.customer.service.dto.CustomerDto;
import com.mall.customer.service.mapper.CustomerServiceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.AdditionalAnswers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private final CustomerServiceMapper mapper = Mappers.getMapper(CustomerServiceMapper.class);
    private final CustomerRepository repository = mock(CustomerRepository.class);
    private final CustomerService service = new CustomerService(repository, mapper);
    private final List<Customer> db = new ArrayList<>();

    @BeforeEach
    @DisplayName("db 초기화 😆")
    void init_db() {
        db.clear();

        db.add(Customer.builder()
                .name("최강승훈")
                .birth(ZonedDateTime.of(LocalDate.of(1999, 12, 25), LocalTime.MAX, ZoneId.of("Asia/Seoul")))
                .address(Address.builder()
                        .city("서울특별시")
                        .street("영등포구 선유로")
                        .detail("더파이러츠")
                        .build())
                .build());

        db.add(Customer.builder()
                .name("대장민형")
                .birth(ZonedDateTime.of(LocalDate.of(1999, 12, 25), LocalTime.MAX, ZoneId.of("Asia/Seoul")))
                .address(Address.builder()
                        .city("서울특별시")
                        .street("영등포구 선유로")
                        .detail("더파이러츠")
                        .build())
                .build());

        db.add(Customer.builder()
                .name("빛현성")
                .birth(ZonedDateTime.of(LocalDate.of(1999, 12, 25), LocalTime.MAX, ZoneId.of("Asia/Seoul")))
                .address(Address.builder()
                        .city("서울특별시")
                        .street("영등포구 선유로")
                        .detail("더파이러츠")
                        .build())
                .build());
    }


    @Test
    @DisplayName("Customer 추가")
    void addCustomer() {
        // given
        CustomerDto dto = CustomerDto.builder()
                .name("최강승훈")
                .birth(ZonedDateTime.of(LocalDate.of(1999, 12, 25), LocalTime.MAX, ZoneId.of("Asia/Seoul")))
                .address(AddressDto.builder()
                        .city("서울특별시")
                        .street("영등포구 선유로")
                        .detail("더파이러츠")
                        .build())
                .build();

        when(repository.save(any(Customer.class))).then(AdditionalAnswers.returnsFirstArg());

        // when
        CustomerDto customerDto = service.addCustomer(dto);

        // then
        assertThat(customerDto.getName()).isEqualTo(dto.getName());
        assertThat(customerDto.getAddress().getCity()).isEqualTo(dto.getAddress().getCity());
        assertThat(customerDto.getAddress().getDetail()).isEqualTo(dto.getAddress().getDetail());
        assertThat(customerDto.getAddress().getStreet()).isEqualTo(dto.getAddress().getStreet());
        assertThat(customerDto.getBirth()).isEqualTo(dto.getBirth());
    }

    @Test
    @DisplayName("모든 Customer 조회")
    void selectCustomerList() {
        // given
        when(repository.findAll()).thenReturn(db);

        // when
        List<CustomerDto> result = service.selectCustomerList();

        // then
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getName()).isEqualTo(db.get(i).getName());
            assertThat(result.get(i).getAddress().getCity()).isEqualTo(db.get(i).getAddress().getCity());
            assertThat(result.get(i).getAddress().getDetail()).isEqualTo(db.get(i).getAddress().getDetail());
            assertThat(result.get(i).getAddress().getStreet()).isEqualTo(db.get(i).getAddress().getStreet());
            assertThat(result.get(i).getBirth()).isEqualTo(db.get(i).getBirth());
        }

    }
}
