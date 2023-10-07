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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pol.ecom.api.shop.dto.request.BrandRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminBrandControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${header.security.key-token}")
    private String keyToken;
    @Value("${header.security.value-token}")
    private String valueToken;


    @Test
    void createBrandOK() throws Exception {
        BrandRequest request = BrandRequest.builder()
                .name("Iphone")
                .imageURL("/test/iphone.jpg")
                .info("apple phone")
                .build();
        String requestString = objectMapper.writeValueAsString(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/api/brand")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(keyToken, valueToken)
                .content(requestString)
        ).andExpect(status().isOk());

    }
    @Test
    void createBrand403() throws Exception {
        BrandRequest request = BrandRequest.builder()
                .name("Iphone")
                .imageURL("/test/iphone.jpg")
                .info("apple phone")
                .build();
        String requestString = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/api/brand")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(keyToken, valueToken + "123Abj")
                .content(requestString)
        ).andExpect(status().is4xxClientError());

    }


}
