package com.onepoint.kata.eshopping.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorInfo {
    private String errorMessage;
}
