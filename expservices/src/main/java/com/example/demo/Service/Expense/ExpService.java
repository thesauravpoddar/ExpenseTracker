package com.example.demo.Service.Expense;

import com.example.demo.Service.Expense.model.ExpenseData;
import com.example.demo.data.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpService {
     Expense createExpense(String userid, ExpenseData expenseData);

    Expense getExpById(String userid, String expId);

    Page<Expense> listExpById(String userid , Pageable pageable , String StartDate , String EndDate);


    Expense updateExpense(String userid, String expId, ExpenseData expenseData);

    void deleteExpense(String userid, String expId);
}
