package com.ostapchuk.app.groshik.controller;

import com.ostapchuk.app.groshik.dto.ExpenseDto;
import com.ostapchuk.app.groshik.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/api/v1/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public List<ExpenseDto> findAll() {
        List<ExpenseDto> expenses = expenseService.findAll();
        log.info("Finding the expenses, amount is: {}", expenses.size());
        return expenses.stream()
                .map(e -> e.add(
                        linkTo(methodOn(ExpenseController.class).findOne(e.getId())).withRel("findExpense"),
                        linkTo(methodOn(ExpenseController.class).update(e.getId(), e)).withRel("updateExpense"),
                        linkTo(methodOn(ExpenseController.class).save(e)).withRel("saveExpense")))
                .collect(toList());
    }

    @GetMapping("/{id}")
    public ExpenseDto findOne(@PathVariable String id) {
        ExpenseDto expense = expenseService.findOne(id);
        log.info("Finding an expense by id: {}", id);
        expense.add(
                linkTo(methodOn(ExpenseController.class).findOne(id)).withSelfRel(),
                linkTo(methodOn(ExpenseController.class).update(id, expense)).withRel("updateExpense"),
                linkTo(methodOn(ExpenseController.class).save(expense)).withRel("saveExpense")
        );
        return expense;
    }

    @PutMapping("/{id}")
    public ExpenseDto update(@PathVariable String id,
                             @RequestBody ExpenseDto newExpenseDto) {
        ExpenseDto expense = expenseService.update(id, newExpenseDto);
        expense.add(
                linkTo(methodOn(ExpenseController.class).update(newExpenseDto.getId(), newExpenseDto)).withSelfRel(),
                linkTo(methodOn(ExpenseController.class).findOne(id)).withRel("findExpense"),
                linkTo(methodOn(ExpenseController.class).save(expense)).withRel("saveExpense")
        );
        return expense;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseDto save(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto expense = expenseService.save(expenseDto);
        expense.add(
                linkTo(methodOn(ExpenseController.class).save(expense)).withSelfRel(),
                linkTo(methodOn(ExpenseController.class).findOne(expense.getId())).withRel("findExpense"),
                linkTo(methodOn(ExpenseController.class).update(expenseDto.getId(), expenseDto)).withRel("updateExpense")
        );
        return expense;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        expenseService.deleteById(id);
    }
}
