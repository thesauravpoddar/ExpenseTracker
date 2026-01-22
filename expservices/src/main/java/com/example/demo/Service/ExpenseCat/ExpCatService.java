package com.example.demo.Service.ExpenseCat;

import com.example.demo.data.model.ExpenseCategory;

import java.util.List;


public interface ExpCatService {
    public ExpenseCategory createExpenseCat(String userid, String name);

    ExpenseCategory getExpCatById(String expCatId, String userid);

    List<ExpenseCategory> listExpCatById(String userid);

    ExpenseCategory updateExpenseCategory(String userid, String expCatId, String name);

    void deleteExpenseCategory(String userid, String expCatId);
}
