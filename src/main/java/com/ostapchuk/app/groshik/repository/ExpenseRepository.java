package com.ostapchuk.app.groshik.repository;

import com.ostapchuk.app.groshik.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
