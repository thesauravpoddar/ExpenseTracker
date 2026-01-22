package com.example.demo.controller;

import com.example.demo.Service.Expense.ExpService;
import com.example.demo.controller.dto.ExpenseCategoryRequest;
import com.example.demo.controller.dto.ExpenseRequest;
import com.example.demo.controller.mapper.ExpReqMapper;
import com.example.demo.data.model.Expense;
import com.example.demo.data.model.ExpenseCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userid}/expense")
@RequiredArgsConstructor
public class ExpController {
private final ExpService expService;
    @PostMapping("/")
   ResponseEntity<Expense> createExpense(
            @PathVariable String userid,
            @RequestBody ExpenseRequest expenseRequest // takes json and converts or maps to java object
   ) {
        // wer can do logging here by using slf4j
       var expense =  expService.createExpense(
                userid,
               ExpReqMapper.INSTANCE.mapToExpenseData(expenseRequest)
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expense);
    }
    @GetMapping("/{ExpId}")
        public ResponseEntity<Expense> getExpenseById(
                @PathVariable String ExpId,
                @PathVariable String userid
        ) {
        var expense = expService.getExpById(
                userid,
                ExpId
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        expense
                );
        }
        @GetMapping("/")
        public ResponseEntity<Page<Expense>> listExpenseById(
                @PathVariable String userid,
                Pageable pageable,
                String StartDate,
                String EndDate
        ) {
        var expense = expService.listExpById(
                userid , pageable , StartDate
                , EndDate
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        expense
                );
        }
    @PutMapping("/{ExpId}")
    ResponseEntity<Expense> UpdateExpense(
            @PathVariable String userid,
            @PathVariable String ExpId,
            @RequestBody ExpenseRequest expenseRequest // takes json and converts or maps to java object
    ) {
        // wer can do logging here by using slf4j
       var expense = expService.updateExpense(
                userid ,
               ExpId ,
               ExpReqMapper.INSTANCE.mapToExpenseData(expenseRequest)
        );
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(expense);
    }
    @DeleteMapping("/{ExpId}")
    ResponseEntity<Void> deleteExpenseCategory(
            @PathVariable String userid,
            @PathVariable String ExpId
    ) {
        expService.deleteExpense(
                userid , ExpId
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
