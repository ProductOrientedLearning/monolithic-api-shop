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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pol.ecom.api.shop.dto.request.ProductRequest;
import pol.ecom.api.shop.dto.response.ProductResponse;
import pol.ecom.api.shop.mapper.dto.ProductDtoMapper;
import pol.ecom.api.shop.mapper.enity.ProductMapper;
import pol.ecom.api.shop.repository.ProductRepository;
import pol.ecom.api.shop.service.ProductService;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDtoMapper productDtoMapper;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest request) {
        return productDtoMapper.toDto(productRepository.save(productMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse getById(UUID uuid) {
        return productDtoMapper.toDto(productRepository.findById(uuid).orElse(null));
    }
}
