package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.Budget;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
  Optional<Budget> findByEmail(String email);

  Optional<Budget> findByEmailAndName(String email, String name);

  Optional<Budget> findExactBudget(String email, String name, LocalDate period);
}
