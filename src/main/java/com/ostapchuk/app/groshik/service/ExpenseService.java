package com.ostapchuk.app.groshik.service;

import com.ostapchuk.app.groshik.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto findOne(String id);

    List<ExpenseDto> findAll();

    ExpenseDto save(ExpenseDto expenseDto);

    ExpenseDto update(String id, ExpenseDto newExpenseDto);

    void deleteById(String id);
}
