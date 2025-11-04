package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.service.TransactionService;
import com.wolf.budgetapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring",
    uses = {UserService.class, TransactionService.class, BudgetIdDtoMapper.class})
public interface BudgetDtoMapper {
  Budget toEntity(BudgetDto budgetDto) throws EntityNotFoundException;

  BudgetDto toDto(Budget budget) throws EntityNotFoundException;

  List<BudgetDto> toDtoList(List<Budget> budgetList) throws EntityNotFoundException;

  @Mapping(target = "user", source = "userEmail")
  @Mapping(target = "amount", source = "amount")
  @Mapping(target = "category", ignore = true)
  void updateEntity(BudgetDto to, @MappingTarget Budget entity);
}
