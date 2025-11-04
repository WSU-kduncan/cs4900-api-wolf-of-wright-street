package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.model.TransactionCategory;
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
  @Mapping(target = "user", source = "userEmail")
  @Mapping(target = "category", source = "categoryName")
  @Mapping(target = "id", source = "id")
  Budget toEntity(BudgetDto budgetDto) throws EntityNotFoundException;

  @Mapping(target = "userEmail", source = "user.emailAddress")
  @Mapping(target = "categoryName", source = "category.categoryName")
  BudgetDto toDto(Budget budget) throws EntityNotFoundException;

  List<BudgetDto> toDtoList(List<Budget> budgetList) throws EntityNotFoundException;

  @Mapping(target = "user", source = "userEmail")
  @Mapping(target = "category", source = "categoryName")
  void updateEntity(BudgetDto to, @MappingTarget Budget entity);

  default TransactionCategory map(String categoryName) {
    if (categoryName == null) return null;
    TransactionCategory category = new TransactionCategory();
    category.setCategoryName(categoryName);
    return category;
  }
}
