package com.example.demo.data.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@CompoundIndex(
        name = "expCatId_userId",
        def = "{'expenseCatId': 1, 'userId': 1}",
        unique = true
)
@Document(collection = "expense_categories")
public class ExpenseCategory {
    @Id
    private String expenseCatId;
    private String userId;
    private String name;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
