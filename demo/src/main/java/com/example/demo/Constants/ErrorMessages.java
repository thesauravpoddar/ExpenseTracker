package com.example.demo.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    EXP_CAT_ALR_EXISTS("Expense Category Already Exists", "EXP_001"),
    EXP_CAT_NOT_FOUND("Expense Category Not Found", "EXP_404");
    private final String ErrorMessage;
    private final String ErrorCode;
}
