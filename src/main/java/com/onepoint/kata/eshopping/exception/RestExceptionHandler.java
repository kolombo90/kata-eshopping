package com.onepoint.kata.eshopping.exception;


import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorInfo> validationExceptionHandler(MethodArgumentNotValidException ex) {
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        List<ErrorInfo> errorInfos = new ArrayList<>();
        errorList.stream().map(ObjectError::getDefaultMessage).forEach(msg -> errorInfos.add(ErrorInfo.builder().errorMessage(msg).build()));
        return errorInfos;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo parseExceptionHandler(HttpMessageNotReadableException ex) {
        return ErrorInfo.builder().errorMessage("Could not parse the input object please check").build();
    }

    @ExceptionHandler(RessourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo accountNotFoundExceptionHandler (RessourceNotFoundException ex) {
        ErrorInfo errorInfo = ErrorInfo.builder().errorMessage(ex.getMessage()).build();
        return errorInfo;
    }

}
