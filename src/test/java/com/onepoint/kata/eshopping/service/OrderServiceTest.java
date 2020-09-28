package com.onepoint.kata.eshopping.service;

import com.onepoint.kata.eshopping.dto.OrderDto;
import com.onepoint.kata.eshopping.dto.OrderLineDto;
import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.repository.OrderRepository;
import com.onepoint.kata.eshopping.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void should_throw_exception_when_product_is_not_found(){
        OrderLineDto orderLine = OrderLineDto.builder().productId(5L).quantity(2).build();
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        orderLineDtoList.add(orderLine);

        OrderDto orderDto = OrderDto.builder().status("PAID").orderLineDtoList(orderLineDtoList).build() ;
        Assertions.assertThrows(RessourceNotFoundException.class, () -> {
            orderService.addOrder(orderDto);
        });
    }

    @Test
    public void reduction_amount_should_be_zero(){
        assertEquals (orderService.calculateReduction(20f),0);
    }

    @Test
    public void reduction_amount_should_be_positif(){
        assertEquals (orderService.calculateReduction(2000f),100);
    }
}