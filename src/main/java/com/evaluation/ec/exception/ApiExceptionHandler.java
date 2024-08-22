package com.evaluation.ec.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomErrorResponse.class)
    public ResponseEntity<Object> handleEmailAlreadyRegistered(CustomErrorResponse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getMessage()));
    }

}
