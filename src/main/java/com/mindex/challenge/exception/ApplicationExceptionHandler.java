package com.mindex.challenge.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exp){
        Map<String, String> argumentErrorMap = new HashMap<>();
        exp.getBindingResult().getFieldErrors().forEach(error ->{
            argumentErrorMap.put(error.getField(), error.getDefaultMessage());
        });

        return argumentErrorMap;
    }
}