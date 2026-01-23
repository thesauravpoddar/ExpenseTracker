package tracker.expensereport.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReportResponse {
    private WeeklyReport weeklyReport;
    private MonthlyReport monthlyReport;
}
