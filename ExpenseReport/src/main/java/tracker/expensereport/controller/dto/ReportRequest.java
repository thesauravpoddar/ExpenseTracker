package tracker.expensereport.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportRequest {
    private List<Expense> expenses;
}
