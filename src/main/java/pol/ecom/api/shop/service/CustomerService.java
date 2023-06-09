package pol.ecom.api.shop.service;
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

import org.springframework.data.domain.Pageable;
import pol.ecom.api.shop.dto.request.CustomerRequest;
import pol.ecom.api.shop.dto.response.CustomerPageResponse;
import pol.ecom.api.shop.dto.response.CustomerResponse;

public interface CustomerService {

    /**
     * This function is for creating new customers,
     * we should be validated the format of the email and phone,
     * and check conditions not to allow duplicate emails
     * because the email will use as an account sign-in to the system.
     * @param request CustomerRequest
     * @return CustomerResponse
     */
    CustomerResponse createCustomer(CustomerRequest request);

    /**
     * This function allows search for customers according to info: name, phone, email, and address of the customer.
     * @param textSearch String.
     * @return CustomerResponse pages.
     */
    CustomerPageResponse searchCustomer(String textSearch, Pageable pageable);
}
