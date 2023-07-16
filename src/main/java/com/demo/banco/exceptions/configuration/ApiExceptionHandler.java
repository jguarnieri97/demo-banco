package com.demo.banco.exceptions.configuration;

import com.demo.banco.exceptions.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ ServiceException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(ServiceException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getStatus().value(), ex.getMessage(), Instant.now()), ex.getStatus());
    }
}
