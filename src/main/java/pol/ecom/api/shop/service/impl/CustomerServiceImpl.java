package pol.ecom.api.shop.service.impl;
/*
 * This is course Microservice Product Oriented
 * MIT No Attribution

 * Copyright (c) <2023> <Dr.JohnLe & Mr.HaNguyen>

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pol.ecom.api.shop.dto.request.CustomerRequest;
import pol.ecom.api.shop.dto.response.CustomerPageResponse;
import pol.ecom.api.shop.dto.response.CustomerResponse;
import pol.ecom.api.shop.entity.Customer;
import pol.ecom.api.shop.mapper.dto.CustomerDtoMapperImpl;
import pol.ecom.api.shop.mapper.enity.CustomerMapperImpl;
import pol.ecom.api.shop.repository.CustomerRepository;
import pol.ecom.api.shop.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapperImpl customerMapper;
    @Autowired
    private CustomerDtoMapperImpl customerDtoMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        log.info("process in service create a customer");
        return customerDtoMapper.toDto(customerRepository.save(customerMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerPageResponse searchCustomer(String textSearch, Pageable pageable) {
        CustomerPageResponse customerPageResponse = new CustomerPageResponse();
        Page<Customer> customerResponsePage = customerRepository.searchCustomer(textSearch, pageable);
        long totalRows = 0;
        int totalPage = 0;
        if(!ObjectUtils.isEmpty(customerResponsePage)) {
            totalRows = customerPageResponse.getTotalRows();
            totalPage = customerResponsePage.getTotalPages();
            customerPageResponse.setCustomerResponseList(customerDtoMapper.toListDto(customerResponsePage.getContent()));
        } else {
            customerPageResponse.setCustomerResponseList(new ArrayList<>());
        }
        customerPageResponse.setSizePage(pageable.getPageSize());
        customerPageResponse.setPage(pageable.getPageNumber());
        customerPageResponse.setTotalPage(totalPage);
        customerPageResponse.setTotalRows(totalRows);
        return customerPageResponse;
    }
}
