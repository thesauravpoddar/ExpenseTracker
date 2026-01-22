package com.example.demo.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ExpenseRequest {
    private String title;
    private String amount;
    private String expCatId;
}
