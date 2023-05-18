package pol.ecom.api.shop.mapper.enity;
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
import org.springframework.stereotype.Component;
import pol.ecom.api.shop.constant.CommonConstants;
import pol.ecom.api.shop.dto.request.ProductRequest;
import pol.ecom.api.shop.entity.Product;
import pol.ecom.api.shop.mapper.EntityMapper;
import pol.ecom.api.shop.repository.BrandRepository;

@Component
public class ProductMapper implements EntityMapper<ProductRequest, Product> {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Product toEntity(ProductRequest request) {

        String image = String.join(CommonConstants.ARRAY_SEPARATOR, request.getImages());

        return Product.builder()
                .name(request.getName())
                .info(request.getInfo())
                .unit(request.getUnit())
                .brand(brandRepository.findById(request.getBrandId()).orElse(null))
                .image(image)
                .price(request.getPrice())
                .specialPrice(request.getSpecialPrice())
                .sku(request.getSku())
                .inStock(request.isInStock())
                .active(request.isActive())
                .fromDate(request.getFromDate())
                .toDate(request.getToDate())
                .build();
    }
}
