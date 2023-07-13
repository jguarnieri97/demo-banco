package com.demo.banco.exceptions.configuration;

import com.demo.banco.exceptions.RegisterUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ RegisterUserException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(RegisterUserException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getStatus().value(), ex.getMessage(), Instant.now()), ex.getStatus());
    }
}
