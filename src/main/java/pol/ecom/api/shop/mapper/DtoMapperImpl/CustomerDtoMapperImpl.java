package pol.ecom.api.shop.mapper.DtoMapperImpl;
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

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pol.ecom.api.shop.dto.response.CustomerResponse;
import pol.ecom.api.shop.entity.Customer;
import pol.ecom.api.shop.mapper.DtoMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDtoMapperImpl implements DtoMapper<Customer, CustomerResponse> {
    @Override
    public CustomerResponse toDto(Customer entity) {
        return CustomerResponse.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .account(entity.getAccount())
                .build();
    }

    @Override
    public List<CustomerResponse> toListDto(List<Customer> entities) {
        List<CustomerResponse> responses = new ArrayList<>();
        if(!ObjectUtils.isEmpty(entities)) {
            for (Customer entity : entities) {
                responses.add(toDto(entity));
            }
        }
        return responses;
    }
}
