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

  // Reminder of existing JPA methods:
  // - findAll()
  // - findById()
  // - save()
  // - deleteById()

  // want to get transaction per user
  List<Transaction> findByUser(User user);

  // want to get transactions by user and category
  List<Transaction> findByUserAndCategory(User user, TransactionCategory category);

  // want by user and category and date range
  List<Transaction> findByUserAndCategoryAndTransactionDateTimeBetween(
      User user, TransactionCategory category, Instant startDate, Instant endDate);
}
