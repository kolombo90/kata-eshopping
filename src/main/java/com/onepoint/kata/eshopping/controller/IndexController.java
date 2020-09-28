package com.onepoint.kata.eshopping.controller;

import com.onepoint.kata.eshopping.model.Product;
import com.onepoint.kata.eshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the kata application. you can simulate bank account managment with this rest api app";
    }
}