package tracker.expensereport.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tracker.expensereport.conatants.LoggingConstants;
import tracker.expensereport.controller.dto.Expense;
import tracker.expensereport.controller.dto.MonthlyReport;
import tracker.expensereport.controller.dto.WeeklyReport;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportsServiceImpl implements  ReportsService {
    @Override
    public WeeklyReport getWeeklyReport(String userId, List<Expense> expenses) {
        var methodName = "ReportsServiceImpl:getWeeklyReport";
        log.info(LoggingConstants.START_METHOD_UPDATE, methodName, userId);

       var totalExpensesByDate = expenses.stream()
                        .collect(
                                Collectors.groupingBy(Expense::getCreatedAt ,
                                        Collectors.summingDouble(Expense::getAmount))
                                );
       var totalExpenses = totalExpensesByDate.values()
               .stream()
               .mapToDouble(value -> value)
                                        .sum();
      var weeklyReport = WeeklyReport.builder()
                .totalExpensesByDay(totalExpensesByDate)
                .totalExpenses(totalExpenses)
                .build();
        log.info(LoggingConstants.END_METHOD_UPDATE, methodName, userId);
        return weeklyReport;
    }

    @Override
    public MonthlyReport getMonthlyReport(String userId, List<Expense> expenses) {
        var methodName = "ReportsServiceImpl:getMonthlyReport";
        log.info(LoggingConstants.START_METHOD_UPDATE, methodName, userId);

        var totalExpenseByCategory = expenses.stream()
                .collect(
                        Collectors.groupingBy(Expense::getExpenseCategory ,
                                Collectors.summingDouble(Expense::getAmount))
                );

        var totalExpenses = totalExpenseByCategory.values()
                .stream()
                .mapToDouble(value -> value)
                .sum();
        var monthlyReport = MonthlyReport.builder()
                        .totalExpensesByCategory(totalExpenseByCategory)
                .totalExpenses(totalExpenses)
                .build();

        log.info(LoggingConstants.END_METHOD_UPDATE, methodName, userId);

        return monthlyReport;
    }
}
