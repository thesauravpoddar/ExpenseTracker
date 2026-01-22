package com.example.demo.controller;

import com.example.demo.Service.ExpenseCat.ExpCatService;
import com.example.demo.controller.dto.ExpenseCategoryRequest;
import com.example.demo.data.model.ExpenseCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userid}/categories")
@RequiredArgsConstructor
public class ExpCategoryController {
private final ExpCatService expCatService;
    @PostMapping("/")
   ResponseEntity<ExpenseCategory> createExpenseCategory(
            @PathVariable String userid,
            @RequestBody ExpenseCategoryRequest expenseCategoryRequest // takes json and converts or maps to java object
   ) {
        // wer can do logging here by using slf4j
       var expenseCategory =  expCatService.createExpenseCat(
                userid,
                expenseCategoryRequest.getName()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseCategory);
    }
    @GetMapping("/{ExpCatId}")
        public ResponseEntity<ExpenseCategory> getExpenseCategoryById(
                @PathVariable String ExpCatId,
                @PathVariable String userid
        ) {
        var expenseCategory = expCatService.getExpCatById(
                userid,
                ExpCatId
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        expenseCategory
                );
        }
        @GetMapping("/")
        public ResponseEntity<List<ExpenseCategory>> listExpenseCategoryById(
                @PathVariable String userid
        ) {
        var expenseCategories = expCatService.listExpCatById(
                userid
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        expenseCategories
                );
        }
    @PutMapping("/{ExpCatId}")
    ResponseEntity<ExpenseCategory> UpdateExpenseCategory(
            @PathVariable String userid,
            @PathVariable String ExpCatId,
            @RequestBody ExpenseCategoryRequest expenseCategoryRequest // takes json and converts or maps to java object
    ) {
        // wer can do logging here by using slf4j
       var expenseCategory = expCatService.updateExpenseCategory(
                userid , ExpCatId , expenseCategoryRequest.getName()
        );
       return ResponseEntity.status(HttpStatus.OK)
               .body(expenseCategory);
    }
    @DeleteMapping("/{ExpCatId}")
    ResponseEntity<Void> deleteExpenseCategory(
            @PathVariable String userid,
            @PathVariable String ExpCatId
    ) {
        expCatService.deleteExpenseCategory(
                userid , ExpCatId
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
