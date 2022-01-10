package com.ostapchuk.app.groshik.mapper;

import com.ostapchuk.app.groshik.dto.ExpenseDto;
import com.ostapchuk.app.groshik.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    ExpenseDto toDto(Expense expense);

    Expense toEntity(ExpenseDto expenseDto);
}
