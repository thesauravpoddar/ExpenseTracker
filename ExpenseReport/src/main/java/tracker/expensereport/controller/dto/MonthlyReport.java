package tracker.expensereport.controller.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MonthlyReport {
    private Map<String , Double> totalExpensesByCategory;
    private Double totalExpenses;
}
