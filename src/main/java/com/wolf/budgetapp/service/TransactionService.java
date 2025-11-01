package com.wolf.budgetapp.service;

import com.wolf.budgetapp.dto.TransactionDto;
import com.wolf.budgetapp.mapper.TransactionDtoMapper;
import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionDtoMapper transactionDtoMapper;

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }

  public Transaction getTransactionById(Long id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Transaction ID (" + id + ") not found"));
  }

  public Transaction createTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  public void deleteTransaction(Long id) {
    if (!transactionRepository.existsById(id)) {
      throw new EntityNotFoundException("Transaction ID (" + id + ") not found");
    }
    transactionRepository.deleteById(id);
  }

  public List<Transaction> getTransactionsByUser(User user) {
    return transactionRepository.findByUser(user);
  }

  public List<Transaction> getTransactionsByUserAndCategory(
      User user, TransactionCategory category) {
    return transactionRepository.findByUserAndCategory(user, category);
  }

  public List<Transaction> getTransactionsByUserCategoryAndDateRange(
      User user, TransactionCategory category, Instant startDate, Instant endDate) {
    return transactionRepository.findByUserAndCategoryAndTransactionDateTimeBetween(
        user, category, startDate, endDate);
  }

  // PUT (UPDATE)
  public Transaction updateTransactionByID(Long id, TransactionDto dto) {
    if (dto.getId() == null) {
      throw new IllegalArgumentException("Transaction ID is required");
    }

    Transaction existingTransaction = transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Transaction ID (" + id + ") not found"));

    // Update fields from DTO (ignoring relationships if needed)
    transactionDtoMapper.updateEntity(dto, existingTransaction);

    return transactionRepository.save(existingTransaction);
  }

  public List<Transaction> getTransactionsByCategoryName(String categoryName) {
    return transactionRepository.findByCategory_CategoryName(categoryName);
  }
}
