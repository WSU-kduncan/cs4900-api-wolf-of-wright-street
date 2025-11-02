package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  // Find transactions by user
  List<Transaction> findByUser(User user);

  // Find transactions by user and category
  List<Transaction> findByUserAndCategory(User user, TransactionCategory category);

  // Find transactions by user, category, and date range
  List<Transaction> findByUserAndCategoryAndTransactionDateTimeBetween(
      User user, TransactionCategory category, Instant startDate, Instant endDate);

  // Find transactions by category name (nested property)
  List<Transaction> findByCategory_CategoryName(String categoryName);
}
