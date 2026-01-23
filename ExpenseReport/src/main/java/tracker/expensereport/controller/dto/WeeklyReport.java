package tracker.expensereport.controller.dto;

import lombok.*;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WeeklyReport {
    private Map<String , Double> totalExpensesByDay;
    private Double totalExpenses;
}
