package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.TransactionDto;
import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring",
    uses = {UserDtoMapper.class, TransactionCategoryDtoMapper.class})
public interface TransactionDtoMapper {

  @Mapping(source = "user.emailAddress", target = "userEmail")
  @Mapping(source = "category.categoryName", target = "categoryName")
  TransactionDto toDto(Transaction transaction);

  @Mapping(source = "user", target = "user")
  @Mapping(source = "category", target = "category")
  Transaction toEntity(TransactionDto dto, User user, TransactionCategory category);

  @BeanMapping(
      nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "amount", source = "amount")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "transactionDateTime", source = "transactionDateTime")
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "category", ignore = true)
  void updateEntity(TransactionDto dto, @MappingTarget Transaction entity);
}
