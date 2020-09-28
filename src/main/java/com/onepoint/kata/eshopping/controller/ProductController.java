package com.onepoint.kata.eshopping.controller;

import com.onepoint.kata.eshopping.dto.ProductDto;
import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.model.Bill;
import com.onepoint.kata.eshopping.model.Product;
import com.onepoint.kata.eshopping.service.ProductService;
import com.onepoint.kata.eshopping.utilities.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/api/product")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable Long id) throws RessourceNotFoundException {
        return productService.findProduct(id);
    }

    @PostMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable Long id) throws RessourceNotFoundException {
        productService.deleteProduct(id);
    }

    @PostMapping("/api/product/add")
    public void addProduct(@Valid @RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping("/api/product/sort/{field}")
    public List<Product> sortProducts(@PathVariable String field) throws RessourceNotFoundException {

        Set<String> sourceFieldList = MyUtils.getAllFields(Bill.class);
        if (!sourceFieldList.contains(field)) {
            throw new RessourceNotFoundException("sort field does not exist");
        }
        return productService.sortProducts(field);
    }
}
