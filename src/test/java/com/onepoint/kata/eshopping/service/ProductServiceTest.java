package com.onepoint.kata.eshopping.service;

import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void should_throw_exception_when_product_is_not_found(){
        Assertions.assertThrows(RessourceNotFoundException.class, () -> {
            productService.findProduct(2L);
        });
    }

    @Test
    public void should_throw_exception_when_deleting_a_product_which_is_not_found(){
        Assertions.assertThrows(RessourceNotFoundException.class, () -> {
            productService.deleteProduct(2L);
        });
    }


}