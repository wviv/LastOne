package com.wa.last.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author echidna
 * @date 2019/10/11 14:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthenticationException extends RuntimeException {
    private Integer status;
    private String message;

    public AuthenticationException(String message) {
        this.status = 401;
        this.message = message;
    }

    public AuthenticationException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
