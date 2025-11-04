package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.BudgetIdDto;
import com.wolf.budgetapp.model.BudgetID;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BudgetIdDtoMapper {
  @Mapping(target = "userEmail", source = "emailAddress")
  BudgetIdDto toDto(BudgetID budgetId) throws EntityNotFoundException;

  @Mapping(target = "emailAddress", source = "userEmail")
  BudgetID toEntity(BudgetIdDto budgetIdDto) throws EntityNotFoundException;

  @Mapping(target = "emailAddress", source = "userEmail")
  void updateEntity(BudgetIdDto to, @MappingTarget BudgetID entity);
}
