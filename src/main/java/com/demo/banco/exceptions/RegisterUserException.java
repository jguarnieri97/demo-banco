package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;

public class RegisterUserException extends ServiceException {

    public RegisterUserException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }


}
