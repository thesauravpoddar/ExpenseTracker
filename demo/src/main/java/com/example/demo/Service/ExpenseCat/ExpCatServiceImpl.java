package com.example.demo.Service.ExpenseCat;

import com.example.demo.Constants.ErrorMessages;
import com.example.demo.data.model.ExpenseCategory;
import com.example.demo.data.repo.ExpCatRepo;
import com.example.demo.exception.CatNotfoundException;
import com.example.demo.exception.ExpenseCatAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpCatServiceImpl implements ExpCatService{
    private final ExpCatRepo expCatRepo;
    @Override
    public ExpenseCategory createExpenseCat(String userid, String name) {
        //we can do logging here by using
        // here we first will check if the expense category with that name already exists or not
        if(expCatRepo.existsByUserIdAndName(userid , name)) {
            throw new ExpenseCatAlreadyExistsException(
                    ErrorMessages.EXP_CAT_ALR_EXISTS.getErrorMessage(),
                    ErrorMessages.EXP_CAT_ALR_EXISTS.getErrorCode()
            );
        }
        //if not exists we will create a new expense category
        // create new expense category object
        var expenseCat = ExpenseCategory.builder()
                .name(name)
                .userId(userid.toLowerCase())
                .build();
        //save it to the database
        return expCatRepo.save(expenseCat);
    }

    @Override
    public ExpenseCategory getExpCatById(String userid, String expCatId) {

        return expCatRepo.findByExpCatIdAndUserId(expCatId , userid).orElseThrow(
                 () -> {
                     return new CatNotfoundException(
                             ErrorMessages.EXP_CAT_NOT_FOUND.getErrorMessage(),
                             ErrorMessages.EXP_CAT_NOT_FOUND.getErrorCode()
                     );
                 }

         );
    }

    @Override
    public List<ExpenseCategory> listExpCatById(String userid) {
        return List.of();
    }
}
