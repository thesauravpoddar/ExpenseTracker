package com.example.demo.data.repo;

import com.example.demo.data.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.Optional;

@EnableMongoRepositories
public interface ExpRepo extends MongoRepository<Expense,String> {

    Optional<Expense> findByExpenseIdAndUserId(String userid , String expId);
    Page<Expense> findAllByUserId(String userid, Pageable pageable);
    Page<Expense> findAllUserByUserIdAndCreatedAtBetween(String userid, LocalDateTime localDateTime, LocalDateTime localDateTime1 , Pageable pageable);
}
