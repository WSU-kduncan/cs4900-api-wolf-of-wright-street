package com.wolf.budgetapp.mapper;

import org.mapstruct.Mapper;
import com.wolf.budgetapp.dto.BudgetIdDto;
import com.wolf.budgetapp.model.BudgetID;
import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public interface BudgetIdDtoMapper {
    BudgetIdDto toDto(BudgetID budgetId) throws EntityNotFoundException;
    BudgetID toEntity(BudgetIdDto budgetIdDto) throws EntityNotFoundException;
}
