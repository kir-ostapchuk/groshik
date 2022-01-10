package com.ostapchuk.app.groshik.service.impl;

import com.ostapchuk.app.groshik.dto.ExpenseDto;
import com.ostapchuk.app.groshik.mapper.ExpenseMapper;
import com.ostapchuk.app.groshik.model.Expense;
import com.ostapchuk.app.groshik.repository.ExpenseRepository;
import com.ostapchuk.app.groshik.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseDto findOne(String id) {
        return expenseRepository.findById(id)
                .map(expenseMapper::toDto)
                .orElseThrow(NoSuchElementException::new); // TODO: 8/1/2021
    }

    @Override
    public List<ExpenseDto> findAll() {
        return expenseRepository.findAll().stream()
                .map(expenseMapper::toDto)
                .collect(toList());
    }

    @Override
    public ExpenseDto save(ExpenseDto expenseDto) {
        return expenseMapper.toDto(expenseRepository.save(expenseMapper.toEntity(expenseDto)));
    }

    @Override
    public ExpenseDto update(String id, ExpenseDto newExpenseDto) {

//        expenseRepository.existsById(id)

        Expense oldExpense = expenseRepository.findById(id)
                .orElseThrow(NoSuchElementException::new); // TODO: 8/1/2021
        return expenseMapper.toDto(
                expenseRepository.save(expenseMapper.toEntity(newExpenseDto).setId(oldExpense.getId()))
        );
    }

    @Override
    public void deleteById(String id) {
        expenseRepository.deleteById(id);
    }
}
