package com.onepoint.kata.eshopping.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;

@Builder
@Data
public class OrderLineDto {
    @NonNull
    private Long productId;
    @Min(value = 1, message = "product quantity should be more than 1")
    private int quantity;
}
