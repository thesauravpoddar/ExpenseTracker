package com.example.demo.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
}
