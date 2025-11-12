package com.wolf.budgetapp.service;

import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.mapper.BudgetDtoMapper;
import com.wolf.budgetapp.mapper.BudgetIdDtoMapper;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.BudgetRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BudgetService {

  private final BudgetRepository budgetRepository;
  private final BudgetDtoMapper budgetDtoMapper;
  private final BudgetIdDtoMapper budgetIdDtoMapper;

  public List<Budget> getBudgets() {
    return budgetRepository.findAll();
  }

  public List<Budget> getBudgetsByUser(User user) throws EntityNotFoundException {
    return budgetRepository.findByUser(user);
  }

  public List<Budget> getBudgetsByUserAndCategory(User user, TransactionCategory category)
      throws EntityNotFoundException {
    return budgetRepository.findByUserAndCategory(user, category);
  }

  public Budget getExactBudget(User user, TransactionCategory category, LocalDate period)
      throws EntityNotFoundException {
    return budgetRepository.findByUserAndCategoryAndIdBudgetPeriod(user, category, period);
  }

  public Budget addBudget(BudgetDto budgetDto) throws EntityNotFoundException {
    return budgetRepository.saveAndFlush(budgetDtoMapper.toEntity(budgetDto));
  }

  public Budget updateBudget(
      User user, TransactionCategory category, LocalDate period, BudgetDto budgetDto)
      throws EntityNotFoundException, IllegalArgumentException {
    Budget existingBudget =
        budgetRepository.findByUserAndCategoryAndIdBudgetPeriod(user, category, period);
    budgetDtoMapper.updateEntity(budgetDto, existingBudget);
    return budgetRepository.save(existingBudget);
  }

  public void deleteBudget(User user, TransactionCategory category, LocalDate period)
      throws EntityNotFoundException {
    Budget budget = budgetRepository.findByUserAndCategoryAndIdBudgetPeriod(user, category, period);
    budgetRepository.delete(budget);
  }
}
