package com.onepoint.kata.eshopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onepoint.kata.eshopping.dto.OrderDto;
import com.onepoint.kata.eshopping.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private ObjectMapper mapper = new ObjectMapper();
    OrderDto orderDto;


    @Test
    public void should_send_400_status_when_order_is_without_product() throws Exception {
        orderDto = OrderDto.builder().build();

        mockMvc.perform(post("/api/order/add")
                .content(mapper.writeValueAsString(orderDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

}