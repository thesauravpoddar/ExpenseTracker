package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ExpenseNotFoundException extends RuntimeException {
    private final String errorCode;
    public ExpenseNotFoundException(String message , String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
