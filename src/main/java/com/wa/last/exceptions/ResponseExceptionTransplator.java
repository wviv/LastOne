package com.wa.last.exceptions;

import org.springframework.http.ResponseEntity;

public interface ResponseExceptionTransplator<T> {

    ResponseEntity<T> translate(Exception e);
}
