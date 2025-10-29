package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.mapper.TransactionCategoryDtoMapper;
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
}
