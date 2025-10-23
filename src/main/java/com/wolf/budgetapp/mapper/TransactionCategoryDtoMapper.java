package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.model.TransactionCategory;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionCategoryDtoMapper {
    
    TransactionCategoryDto toDto(TransactionCategory transactionCategory) throws EntityNotFoundException;

    TransactionCategory toEntity(TransactionCategoryDto transactionCategoryDto) throws EntityNotFoundException;

    List<TransactionCategoryDto> toDtoList(List<TransactionCategory> transactionCategoryList) throws EntityNotFoundException;
}
