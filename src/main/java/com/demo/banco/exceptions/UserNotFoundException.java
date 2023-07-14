package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException{
    public UserNotFoundException() {
        super("User with provided token not found", HttpStatus.NOT_FOUND);
    }
}
