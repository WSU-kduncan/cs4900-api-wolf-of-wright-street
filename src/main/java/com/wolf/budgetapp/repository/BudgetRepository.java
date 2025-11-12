package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.model.BudgetID;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, BudgetID> {
  // Find by User
  List<Budget> findByUser(User user);

  List<Budget> findByUserAndCategory(User user, TransactionCategory category);

  Budget findByUserAndCategoryAndIdBudgetPeriod(
      User user, TransactionCategory category, LocalDate period);
}
