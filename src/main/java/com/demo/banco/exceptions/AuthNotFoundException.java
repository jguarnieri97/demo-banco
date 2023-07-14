package com.demo.banco.exceptions;

import org.springframework.http.HttpStatus;

public class AuthNotFoundException extends ServiceException{

    public AuthNotFoundException() {
        super("Can't find token authorization", HttpStatus.NOT_FOUND);
    }
}
