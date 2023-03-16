package com.wa.last.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidExceptionTransplator implements ResponseExceptionTransplator<String> {

    @Override
    public ResponseEntity<String> translate(Exception e) {
        return new ResponseEntity(HttpStatus.BAD_GATEWAY);
    }
}
