package com.wa.last.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public String authenticationAdvice(AuthenticationException authenticationException){
        return "奥利给";
    }
}
