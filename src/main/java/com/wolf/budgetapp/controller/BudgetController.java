package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.mapper.BudgetDtoMapper;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import com.wolf.budgetapp.repository.UserRepository;
import com.wolf.budgetapp.service.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/budgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class BudgetController {

  private final BudgetService budgetService;
  private final BudgetDtoMapper budgetDtoMapper;
  private final UserRepository userRepository;
  private final TransactionCategoryRepository transactionCategoryRepository;

  // ALL Budgets (GET)
  @GetMapping
  ResponseEntity<List<BudgetDto>> getBudgets() {
    return new ResponseEntity<>(
        budgetDtoMapper.toDtoList(budgetService.getBudgets()), HttpStatus.OK);
  }

  // Search by User Email (GET)
  @GetMapping(path = "/{email}")
  ResponseEntity<List<BudgetDto>> getBudgetsByEmail(@PathVariable("email") String email) {
    // Resolve user
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Fetch budgets by user
    List<BudgetDto> budgets = budgetService.getBudgetsByUser(user).stream()
        .map(budgetDtoMapper::toDto)
        .collect(Collectors.toList());

    return ResponseEntity.ok(budgets);
  }

  // Search by Email, then by Category Name (GET)
  @GetMapping(path = "/{email}/{name}")
  ResponseEntity<List<BudgetDto>> getBudgetByEmailAndName(
      @PathVariable("email") String email, @PathVariable("name") String name) {
    // Resolve user
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Resolve transaction category
    TransactionCategory category = transactionCategoryRepository
        .findById(name)
        .orElseThrow(
            () -> new EntityNotFoundException("Transaction Category not found with name: " + name));

    // Fetch budgets by user and transaction category
    List<BudgetDto> budgets = budgetService.getBudgetsByUserAndCategory(user, category).stream()
        .map(budgetDtoMapper::toDto)
        .collect(Collectors.toList());

    return ResponseEntity.ok(budgets);
  }

  // Search by Email, then by Category Name, then by Budget Period (GET, exact budget)
  @GetMapping(path = "/{email}/{name}/{period}")
  ResponseEntity<BudgetDto> getExactBudget(
      @PathVariable("email") String email,
      @PathVariable("name") String name,
      @PathVariable("period") LocalDate period) {
    // Resolve user
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Resolve transaction category
    TransactionCategory category = transactionCategoryRepository
        .findById(name)
        .orElseThrow(
            () -> new EntityNotFoundException("Transaction Category not found with name: " + name));

    // Fetch budgets by user and transaction bategory
    BudgetDto budget = budgetDtoMapper.toDto(budgetService.getExactBudget(user, category, period));

    return ResponseEntity.ok(budget);
  }

  // Add new Budget (POST)
  @PostMapping(path = "/addBudget")
  ResponseEntity<Object> addBudget(@RequestBody BudgetDto budgetDto) {
    Budget budget;
    try {
      budget = budgetService.addBudget(budgetDto);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }

  // Edit Specific Budget (PUT)
  @PutMapping(path = "/{email}/{name}/{period}")
  ResponseEntity<Object> updateBudget(
      @PathVariable("email") String email,
      @PathVariable("name") String name,
      @PathVariable("period") LocalDate period,
      @RequestBody BudgetDto budgetDto) {
    try {
      // Resolve user
      User user = userRepository
          .findByEmailAddress(email)
          .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

      // Resolve transaction category
      TransactionCategory category = transactionCategoryRepository
          .findById(name)
          .orElseThrow(() ->
              new EntityNotFoundException("Transaction Category not found with name: " + name));
      // Update Budget via Service, return OK status if successful
      Budget updatedBudget = budgetService.updateBudget(user, category, period, budgetDto);
      return new ResponseEntity<>(budgetDtoMapper.toDto(updatedBudget), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  // Delete Specific Budget (DELETE)
  @DeleteMapping("/{email}/{name}/{period}")
  public ResponseEntity<String> deleteBudget(
      @PathVariable("email") String email,
      @PathVariable("name") String name,
      @PathVariable("period") LocalDate period) {
    try {
      // Resolve user
      User user = userRepository
          .findByEmailAddress(email)
          .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

      // Resolve transaction category
      TransactionCategory category = transactionCategoryRepository
          .findById(name)
          .orElseThrow(() ->
              new EntityNotFoundException("Transaction Category not found with name: " + name));
      budgetService.deleteBudget(user, category, period);
      return new ResponseEntity<>("Budget deleted successfully.", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("Budget not found.", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
