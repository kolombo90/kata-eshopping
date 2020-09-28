package com.onepoint.kata.eshopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onepoint.kata.eshopping.dto.ProductDto;
import com.onepoint.kata.eshopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ObjectMapper mapper = new ObjectMapper();
    ProductDto p;


    @Test
    public void should_send_400_status_when_product_object_is_not_valid() throws Exception {
        p = ProductDto.builder().name("").weight(0.1f).price(0.1f).build();

        mockMvc.perform(post("/api/product/add")
                .content(mapper.writeValueAsString(p))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_send_200_status_when_product_object_is_valid() throws Exception {
        p = ProductDto.builder().name("labtop").weight(0.1f).price(0.1f).build();

        doNothing().when(productService).addProduct(isA(ProductDto.class));

        mockMvc.perform(post("/api/product/add")
                .content(mapper.writeValueAsString(p))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void should_send_400_when_trying_to_sort_with_unknonwen_field() throws Exception {
        mockMvc.perform(get("/api/product/sort/abc")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}