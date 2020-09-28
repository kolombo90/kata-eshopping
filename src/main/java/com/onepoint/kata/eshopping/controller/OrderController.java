package com.onepoint.kata.eshopping.controller;

import com.onepoint.kata.eshopping.dto.OrderDto;
import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.model.Order;
import com.onepoint.kata.eshopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;


    @PostMapping("/api/order/add")
    public void addOrder(@Valid @RequestBody OrderDto orderDto) throws RessourceNotFoundException {
        orderService.addOrder(orderDto);
    }

    @GetMapping("/api/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/api/order/{id}")
    public void deleteOrder(@PathVariable Long id) throws RessourceNotFoundException {
        orderService.deleteOrder(id);
    }
}
