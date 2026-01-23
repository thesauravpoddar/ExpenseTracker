package tracker.expensereport.Service;

import tracker.expensereport.controller.dto.Expense;
import tracker.expensereport.controller.dto.MonthlyReport;
import tracker.expensereport.controller.dto.WeeklyReport;

import java.util.List;

public interface ReportsService {
    WeeklyReport getWeeklyReport(String userId, List<Expense> expenses);

    MonthlyReport getMonthlyReport(String userId, List<Expense> expenses);
}
