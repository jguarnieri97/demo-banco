package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegisterUserException extends RuntimeException {

    public RegisterUserException(String message) {
        super(message);
    }
}
