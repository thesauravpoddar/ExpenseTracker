package tracker.expensereport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.expensereport.Service.ReportsService;
import tracker.expensereport.conatants.LoggingConstants;
import tracker.expensereport.controller.dto.ReportRequest;
import tracker.expensereport.controller.dto.ReportResponse;

@RestController
@RequestMapping("/api/users/{userId}/reports")
@Slf4j
@RequiredArgsConstructor
public class ReportsController {
    private final ReportsService reportsService;
    //weekly report endpoint

    @PostMapping("/weekly-report")
   public ResponseEntity<ReportResponse> getWeeklyReport(
           @PathVariable String userId,
            @RequestBody ReportRequest reportRequest
    ) {
        var methodName = "ReportsController:getWeeklyReport";
        log.info(LoggingConstants.START_METHOD_UPDATE, methodName, userId);

       var weeklyReport =  reportsService.getWeeklyReport(userId , reportRequest.getExpenses());

        log.info(LoggingConstants.END_METHOD_UPDATE, methodName, userId);
        return ResponseEntity.status(HttpStatus.OK).
                body(
                        ReportResponse.builder()
                                .weeklyReport(weeklyReport)
                                .build()
                );
    }
    //monthly report endpoint
    @PostMapping("/Monthly-report")
    public ResponseEntity<ReportResponse> getMonthlyReport(
            @PathVariable String userId,
            @RequestBody ReportRequest reportRequest
    ) {
        var methodName = "ReportsController:getMonthlyReport";
        log.info(LoggingConstants.START_METHOD_UPDATE, methodName, userId);

        var monthlyReport =  reportsService.getMonthlyReport(userId , reportRequest.getExpenses());

        log.info(LoggingConstants.END_METHOD_UPDATE, methodName, userId);
        return ResponseEntity.status(HttpStatus.OK).
                body(
                        ReportResponse.builder()
                                .monthlyReport(monthlyReport)
                                 .build()
                );
    }
}
