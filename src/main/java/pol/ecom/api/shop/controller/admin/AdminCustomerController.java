package pol.ecom.api.shop.controller.admin;
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
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pol.ecom.api.shop.constant.CommonConstants;
import pol.ecom.api.shop.dto.response.CustomerPageResponse;
import pol.ecom.api.shop.service.CustomerService;

@RestController
@RequestMapping(path = "admin/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity<CustomerPageResponse> searchCustomer(@RequestParam(value = "textSearch", required = false) String textSearch,
                                                               @SortDefault(sort = CommonConstants.EntityProperties.NAME, direction = Sort.Direction.ASC)@ParameterObject Pageable pageable ) {
        log.info("search customer");
        return new ResponseEntity<>(customerService.searchCustomer(textSearch, pageable), HttpStatus.OK);

    }
}
