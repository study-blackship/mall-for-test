package com.mall.customer.service;

import com.mall.customer.repository.CustomerRepository;
import com.mall.customer.service.dto.CustomerDto;
import com.mall.customer.service.mapper.CustomerServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceMapper mapper;

    @Transactional
    public CustomerDto addCustomer(CustomerDto dto) {

        return mapper.toDto(customerRepository.save(mapper.toEntity(dto)));

    }
}
