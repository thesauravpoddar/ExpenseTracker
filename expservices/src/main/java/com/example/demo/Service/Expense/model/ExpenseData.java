package com.example.demo.Service.Expense.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ExpenseData {
    private String name;
    private String amount;
    private String expCat;
}
