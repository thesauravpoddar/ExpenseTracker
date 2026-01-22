package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ExpenseCatAlreadyExistsException extends RuntimeException {
    private final String errorCode;

    public ExpenseCatAlreadyExistsException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
