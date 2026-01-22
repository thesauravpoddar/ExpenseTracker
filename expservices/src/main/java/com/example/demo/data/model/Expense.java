package com.example.demo.data.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@CompoundIndex(
        name = "expId_userId",
        def = "{'expenseId': 1, 'userId': 1}",
        unique = true
)
@Document(collection = "expenses")
public class Expense {
    @Id
    private String expenseId;
    private String userId;
    private String title;
    private String amount;

    @DocumentReference
    private ExpenseCategory expCatId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
