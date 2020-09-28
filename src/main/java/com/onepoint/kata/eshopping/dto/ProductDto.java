package com.onepoint.kata.eshopping.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Builder
@Data
public class ProductDto {
    @NotBlank(message = "Product should have a  valid name")
    private String name;

    @DecimalMin(value = "0.1", inclusive = true, message = "product weight should be more than 0.1")
    private float weight;

    @DecimalMin(value = "0.1", inclusive = true, message = "product size should be more than 0.1")
    private float price;
}
