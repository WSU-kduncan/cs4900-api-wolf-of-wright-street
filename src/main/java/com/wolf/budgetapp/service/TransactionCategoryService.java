package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.repository.CashflowTypeRepository;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.mapper.TransactionCategoryDtoMapper;
import com.wolf.budgetapp.model.CashflowType;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionCategoryService {
    
    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionCategoryDtoMapper transactionCategoryDtoMapper;

    private final CashflowTypeRepository cashflowTypeRepository;

    // finds all transaction categories
    public List<TransactionCategory> getAllTransactionCategories() {
        return transactionCategoryRepository.findAll();
    }

    // finds categories by categoryName
    public TransactionCategory getTransactionCategoryByName(String categoryName) {
        TransactionCategory category = transactionCategoryRepository.findById(categoryName)
        .orElseThrow(() -> new EntityNotFoundException("TransactionCategory not found: " + categoryName));

        return category;
    }

    // finds category description associated with given categoryName
    public String getDescriptionByCategoryName(String categoryName) {
        TransactionCategory category = transactionCategoryRepository.findById(categoryName)
        .orElseThrow(() -> new EntityNotFoundException("TransactionCategory not found: " + categoryName));

        return category.getCategoryDescription();
        
    }

    // creates new TransactionCategory
    public TransactionCategory createTransactionCategory(TransactionCategoryDto transactionCategoryDto) {
        return transactionCategoryRepository.saveAndFlush(
            transactionCategoryDtoMapper.toEntity(transactionCategoryDto)
        );
    }

    // updates existing TransactionCategory with any new fields in RequestBody
    public TransactionCategory updateTransactionCategory(String categoryName, TransactionCategoryDto updatedTransactionCategoryDto) {
        TransactionCategory transactionCategory = transactionCategoryRepository.findById(categoryName)
        .orElseThrow(() -> new EntityNotFoundException("TransactionCategory not found: " + categoryName));

        if (updatedTransactionCategoryDto.getCategoryName() != null) {
            transactionCategory.setCategoryName(updatedTransactionCategoryDto.getCategoryName());
        }

        if (updatedTransactionCategoryDto.getCategoryDescription() != null) {
            transactionCategory.setCategoryDescription(updatedTransactionCategoryDto.getCategoryDescription());
        }

        if (updatedTransactionCategoryDto.getCashflowName() != null) {
            CashflowType cashflowType = cashflowTypeRepository.findById(updatedTransactionCategoryDto.getCashflowName())
            .orElseThrow(() -> new EntityNotFoundException("CashFlowType not found: " + updatedTransactionCategoryDto.getCashflowName()));
            
            transactionCategory.setCashflowType(cashflowType);
        }

        return transactionCategoryRepository.saveAndFlush(transactionCategory);

    }
}
