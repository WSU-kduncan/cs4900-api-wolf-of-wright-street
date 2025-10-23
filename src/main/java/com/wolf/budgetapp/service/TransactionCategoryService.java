package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionCategoryService {
    
    private final TransactionCategoryRepository transactionCategoryRepository;

    public List<TransactionCategory> getAllTransactionCategories() {
        return transactionCategoryRepository.findAll();
    }
}
