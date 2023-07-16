package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ServiceException {
    public UserAlreadyExistException(String email) {
        super(String.format("User with mail %s already exist.", email), HttpStatus.CONFLICT);
    }
}
