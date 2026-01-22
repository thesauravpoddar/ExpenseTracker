package com.example.demo.controller.mapper;

import com.example.demo.Service.Expense.model.ExpenseData;
import com.example.demo.controller.dto.ExpenseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpReqMapper {
    ExpReqMapper INSTANCE = Mappers.getMapper(ExpReqMapper.class);
    // it will take expense request and will map to expense data
   ExpenseData mapToExpenseData(ExpenseRequest expenseRequest);
}
