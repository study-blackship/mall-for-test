package com.mall.customer.service;

import com.mall.customer.repository.CustomerRepository;
import com.mall.customer.service.dto.CustomerDto;
import com.mall.customer.service.mapper.CustomerServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceMapper mapper;

    @Transactional
    public CustomerDto addCustomer(CustomerDto dto) {
        return mapper.toDto(customerRepository.save(mapper.toEntity(dto)));
    }

    public List<CustomerDto> selectCustomerList() {
        return customerRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
