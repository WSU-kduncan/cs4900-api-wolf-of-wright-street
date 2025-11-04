package com.wolf.budgetapp.service;

import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.mapper.BudgetDtoMapper;
import com.wolf.budgetapp.mapper.BudgetIdDtoMapper;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.repository.BudgetRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

  public Budget getBudgetByEmail(String email) throws EntityNotFoundException {
    Optional<Budget> result = budgetRepository.findByEmail(email);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Budget with email: " + email + " not found");
    }
    return result.get();
  }

  public Budget getBudgetByEmailAndName(String email, String name) throws EntityNotFoundException {
    Optional<Budget> result = budgetRepository.findByEmailAndName(email, name);
    if (result.isEmpty()) {
      throw new EntityNotFoundException(
          "Budget with email and name: " + email + ", " + name + " not found");
    }
    return result.get();
  }

  public Budget getExactBudget(String email, String name, LocalDate period)
      throws EntityNotFoundException {
    Optional<Budget> result = budgetRepository.findExactBudget(email, name, period);
    if (result.isEmpty()) {
      throw new EntityNotFoundException(
          "Budget with email, name, period: " + email + ", " + name + ", " + period + " not found");
    }
    return result.get();
  }

  public Budget addBudget(BudgetDto budgetDto) throws EntityNotFoundException {
    return budgetRepository.saveAndFlush(budgetDtoMapper.toEntity(budgetDto));
  }

  public Budget updateBudget(String email, String name, LocalDate period, BudgetDto budgetDto)
      throws EntityNotFoundException, IllegalArgumentException {
    // Might need a different check here?
    if (budgetDto.getId() == null) {
      throw new IllegalArgumentException("Missing required arguments!");
    }
    Budget existingBudget = budgetRepository
        .findExactBudget(email, name, period)
        .orElseThrow(() -> new EntityNotFoundException("Budget with email, name, period: " + email
            + ", " + name + ", " + period + " not found"));
    budgetDtoMapper.updateEntity(budgetDto, existingBudget);

    return budgetRepository.save(existingBudget);
  }

  public void deleteBudget(String email, String name, LocalDate period)
      throws EntityNotFoundException {
    Budget budget = budgetRepository
        .findExactBudget(email, name, period)
        .orElseThrow(() -> new EntityNotFoundException(
            "User not found with email, name, period:" + email + ", " + name + ", " + period));
    budgetRepository.delete(budget);
  }

  /*
     *   public void deleteUserByEmail(String email) {
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    userRepository.delete(user);
  }
     */
}
