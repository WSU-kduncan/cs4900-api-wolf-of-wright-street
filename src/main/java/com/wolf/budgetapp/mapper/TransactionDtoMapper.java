package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.TransactionDto;
import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {UserDtoMapper.class}) // TransactionCategoryMapper.class})
public interface TransactionDtoMapper {

  @Mapping(source = "user.emailAddress", target = "userEmail")
  @Mapping(source = "category.categoryName", target = "categoryName")
  TransactionDto toDto(Transaction transaction);

  @Mapping(source = "user", target = "user")
  @Mapping(source = "category", target = "category")
  Transaction toEntity(TransactionDto dto, User user, TransactionCategory category);
}
