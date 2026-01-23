package tracker.expensereport.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Expense {
    private Float amount;
    private String expenseCategory;
    private String createdAt;
}
