package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.repository.CashflowTypeRepository;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.mapper.TransactionCategoryDtoMapper;
import com.wolf.budgetapp.model.CashflowType;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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
        Optional<TransactionCategory> result = transactionCategoryRepository.findByCategoryName(categoryName);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Transaction category (" + categoryName + ") not found");
        }
        return result.get();
    }

    // finds categories by categoryDescription
    public TransactionCategory getTransactionCategoryByDescription(String categoryDescription) {
        Optional<TransactionCategory> result = transactionCategoryRepository.findByCategoryDescription(categoryDescription);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Transaction description (" + categoryDescription + ") not found");
        }
        return result.get();
    }

    public TransactionCategory createTransactionCategory(TransactionCategoryDto transactionCategoryDto) throws EntityNotFoundException {
        return transactionCategoryRepository.saveAndFlush(
            transactionCategoryDtoMapper.toEntity(transactionCategoryDto)
        );
    }

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
            .orElseThrow(() -> new EntityNotFoundException("CashFlowType not found"));
            
            transactionCategory.setCashflowType(cashflowType);
        }

        return transactionCategoryRepository.saveAndFlush(transactionCategory);

    }
}
