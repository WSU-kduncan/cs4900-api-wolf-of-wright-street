package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.TransactionDto;
import com.wolf.budgetapp.mapper.TransactionDtoMapper;
import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.TransactionCategoryRepository;
import com.wolf.budgetapp.repository.UserRepository;
import com.wolf.budgetapp.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;
  private final TransactionDtoMapper transactionDtoMapper;
  private final UserRepository userRepository;
  private final TransactionCategoryRepository categoryRepository;

  // GET all transactions
  @GetMapping
  public ResponseEntity<List<TransactionDto>> getAllTransactions() {
    List<Transaction> transactions = transactionService.getAllTransactions();

    List<TransactionDto> dtoList = transactions.stream()
        .map(transaction -> {
          try {
            return transactionDtoMapper.toDto(transaction);
          } catch (Exception e) {
            // Log the problematic transaction ID and skip it
            System.err.println(
                "Failed to map transaction ID " + transaction.getId() + ": " + e.getMessage());
            return null;
          }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return ResponseEntity.ok(dtoList);
  }

  // GET transaction by ID
  @GetMapping("/{id}")
  public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
    try {
      Transaction transaction = transactionService.getTransactionById(id);
      return ResponseEntity.ok(transactionDtoMapper.toDto(transaction));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // GET transactions by category name
  @GetMapping("/category/{categoryName}")
  public ResponseEntity<List<TransactionDto>> getTransactionsByCategory(
      @PathVariable String categoryName) {
    List<TransactionDto> transactions =
        transactionService.getTransactionsByCategoryName(categoryName).stream()
            .map(transactionDtoMapper::toDto)
            .collect(Collectors.toList());
    return ResponseEntity.ok(transactions);
  }

  // get by user
  // GET transactions by user email
  @GetMapping("/user/{email}")
  public ResponseEntity<List<TransactionDto>> getTransactionsByUser(@PathVariable String email) {

    // Resolve User
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Fetch transactions by user
    List<TransactionDto> transactions = transactionService.getTransactionsByUser(user).stream()
        .map(transactionDtoMapper::toDto)
        .collect(Collectors.toList());

    return ResponseEntity.ok(transactions);
  }

  // get by user and category
  @GetMapping("/user/{email}/category/{categoryName}")
  public ResponseEntity<List<TransactionDto>> getTransactionsByUserAndCategory(
      @PathVariable String email, @PathVariable String categoryName) {

    // Resolve User
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Resolve TransactionCategory
    TransactionCategory category = categoryRepository
        .findById(categoryName)
        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryName));

    // Fetch transactions
    List<TransactionDto> transactions =
        transactionService.getTransactionsByUserAndCategory(user, category).stream()
            .map(transactionDtoMapper::toDto)
            .collect(Collectors.toList());

    return ResponseEntity.ok(transactions);
  }

  // POST create a new transaction
  @PostMapping
  public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto dto) {

    // Resolve User
    User user = userRepository
        .findByEmailAddress(dto.getUserEmail())
        .orElseThrow(
            () -> new EntityNotFoundException("User not found with email: " + dto.getUserEmail()));

    // Resolve TransactionCategory
    TransactionCategory category = categoryRepository
        .findById(dto.getCategoryName())
        .orElseThrow(() ->
            new EntityNotFoundException("TransactionCategory not found: " + dto.getCategoryName()));

    // Map DTO to entity
    Transaction transaction = transactionDtoMapper.toEntity(dto, user, category);

    // Save transaction
    Transaction savedTransaction = transactionService.createTransaction(transaction);

    // Map back to DTO for response
    TransactionDto responseDto = transactionDtoMapper.toDto(savedTransaction);

    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }
  // PUT update transaction
  @PutMapping("/{id}")
  public ResponseEntity<TransactionDto> updateTransaction(
      @PathVariable Long id, @RequestBody TransactionDto dto) {
    try {
      Transaction updated = transactionService.updateTransactionByID(id, dto);
      return ResponseEntity.ok(transactionDtoMapper.toDto(updated));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  // DELETE transaction
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
    try {
      transactionService.deleteTransaction(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
