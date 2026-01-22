package com.example.demo.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ExpenseCategoryRequest {
    private String expCategoryId;
    private String name;
    private String createdAt;
}
