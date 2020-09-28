package com.onepoint.kata.eshopping.service;

import com.onepoint.kata.eshopping.dto.ProductDto;
import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.model.Product;
import com.onepoint.kata.eshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @Transactional
    public void addProduct(ProductDto productDto){
        Product p  = Product.builder()
                .name(productDto.getName())
                .weight(productDto.getWeight())
                .price(productDto.getPrice())
                .build();
        productRepository.save(p);
    }

    @Transactional
    public Product findProduct(Long id) throws RessourceNotFoundException {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Please provide a valid product id"));

        return p;
    }

    @Transactional
    public void deleteProduct(Long id) throws RessourceNotFoundException {

        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Please provide a valid product id"));
        productRepository.delete(p);
    }

    @Transactional
    public List<Product> sortProducts(String creteria) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, creteria));
    }
}
