package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.model.TransactionCategory;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionCategoryDtoMapper {

  // @Mapping specifies how to map String cashflowName in TransactionCategoryDto as cashflowType
  // foreign key
  @Mapping(target = "cashflowName", source = "cashflowType.cashflowName")
  TransactionCategoryDto toDto(TransactionCategory transactionCategory)
      throws EntityNotFoundException;

  @Mapping(target = "cashflowType.cashflowName", source = "cashflowName")
  TransactionCategory toEntity(TransactionCategoryDto transactionCategoryDto)
      throws EntityNotFoundException;

  List<TransactionCategoryDto> toDtoList(List<TransactionCategory> transactionCategoryList)
      throws EntityNotFoundException;
}
