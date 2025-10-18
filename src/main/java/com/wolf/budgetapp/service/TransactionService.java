package com.wolf.budgetapp.service;

// import com.wolf.budgetapp.model.Building;
// import com.wolf.budgetapp.model.Room;
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

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }

  public Transaction getTransactionById(Long id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Transaction ID (" + id + ") not found"));
  }

  public Transaction saveTransaction(Transaction transaction) {
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
}
