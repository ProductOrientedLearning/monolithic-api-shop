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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pol.ecom.api.shop.dto.request.BrandRequest;
import pol.ecom.api.shop.dto.response.BrandResponse;
import pol.ecom.api.shop.mapper.dto.BrandDtoMapper;
import pol.ecom.api.shop.mapper.enity.BrandMapper;
import pol.ecom.api.shop.repository.BrandRepository;
import pol.ecom.api.shop.service.BrandService;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDtoMapper brandDtoMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private BrandRepository brandRepository;


    @Transactional
    @Override
    public BrandResponse create(BrandRequest request) {
        log.info("create brand");
        return brandDtoMapper.toDto(brandRepository.save(brandMapper.toEntity(request)));
    }
}
