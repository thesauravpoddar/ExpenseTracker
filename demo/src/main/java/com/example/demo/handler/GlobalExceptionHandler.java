package com.example.demo.handler;

import com.example.demo.controller.dto.ErrorResponse;
import com.example.demo.exception.CatNotfoundException;
import com.example.demo.exception.ExpenseCatAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseCatAlreadyExistsException.class)
    ResponseEntity<ErrorResponse> handleExpenseCatAlreadyExistsException(ExpenseCatAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.builder()
                                .errorMessage(e.getMessage())
                                .errorCode(e.getErrorCode())
                                .build()
                );
    }
    @ExceptionHandler(CatNotfoundException.class)
    ResponseEntity<ErrorResponse> handleCatNotfoundException(CatNotfoundException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.builder()
                                .errorMessage(e.getMessage())
                                .errorCode(e.getErrorCode())
                                .build()
                );
    }


}
