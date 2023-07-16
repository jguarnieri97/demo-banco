package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserRequestException extends ServiceException {

    public InvalidUserRequestException() {
        super("both params email and password are required", HttpStatus.BAD_REQUEST);
    }
}
