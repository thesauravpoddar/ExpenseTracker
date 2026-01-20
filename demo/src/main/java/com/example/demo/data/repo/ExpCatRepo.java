package com.example.demo.data.repo;

import com.example.demo.data.model.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface ExpCatRepo extends MongoRepository<ExpenseCategory , String> {

    boolean existsByUserIdAndName(String userId , String name);

    Optional<ExpenseCategory> findByExpCatIdAndUserId(String ExpCatId, String userId);


}
