package com.example.demo.Service.Expense;

import com.example.demo.Constants.ErrorMessages;
import com.example.demo.Service.Expense.model.ExpenseData;
import com.example.demo.Service.ExpenseCat.ExpCatService;
import com.example.demo.data.model.Expense;
import com.example.demo.data.repo.ExpRepo;
import com.example.demo.exception.ExpenseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExpServiceImpl implements ExpService {
    private final ExpCatService expCatService;
    private final ExpRepo expRepo;
    @Override
    public Expense createExpense(String userid, ExpenseData expenseData) {
        var expenseCategory = expCatService.getExpCatById(userid , expenseData.getExpCat());
       var expense =  Expense.builder()
                .userId(userid).title(expenseCategory.getName()).amount(expenseData.getAmount()).expCatId(expenseCategory).build();
      return expRepo.save(expense);
    }

    @Override
    public Expense getExpById(String userid, String expId) {
        return expRepo.findByExpenseIdAndUserId(userid , expId).orElseThrow(
                () ->
                {
                    return new ExpenseNotFoundException(
                            ErrorMessages.EXP_NOT_FOUND.getErrorMessage(),
                            ErrorMessages.EXP_NOT_FOUND.getErrorCode()
                    );
                }
        );
    }

    @Override
    public Page<Expense> listExpById(String userid , Pageable pageable , String StartDate , String EndDate) {
        Page<Expense> expenses;
        if(StartDate != null && EndDate != null){
           var dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          expenses = expRepo.findAllUserByUserIdAndCreatedAtBetween(userid ,
                   LocalDate.parse(StartDate , dateTimeFormatter).atStartOfDay(),
                   LocalDate.parse(EndDate , dateTimeFormatter).plusDays(1)
                           .atStartOfDay(),
                   pageable
                   );
            //implement date filter logic here
        } else {
           expenses = expRepo.findAllByUserId(userid , pageable);
        }
        return expenses;
    }

    @Override
    public Expense updateExpense(String userid, String expId, ExpenseData expenseData) {

        //find expense by id
        var expense = getExpById(userid, expId);
        // find expense category by id
        var expenseCategory = expCatService.getExpCatById(userid , expenseData.getExpCat());
        // update expense details
        expense.setTitle(expenseData.getName());
        expense.setAmount(expenseData.getAmount());
        expense.setExpCatId(expenseCategory);
        // save updated expense
        return expRepo.save(expense);

    }

    @Override
    public void deleteExpense(String userid, String expId) {
        //find expense by id
        var expense = getExpById(userid, expId);
        // find expense category by id
        var expenseCategory = expCatService.getExpCatById(userid , expId);
        expRepo.delete(expense);
        expCatService.deleteExpenseCategory(userid , expId);
    }


}
