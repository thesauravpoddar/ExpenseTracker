package com.example.demo.exception;

import lombok.Getter;

@Getter
public class CatNotfoundException extends RuntimeException {
    private final String errorCode;
    public CatNotfoundException(String message , String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
