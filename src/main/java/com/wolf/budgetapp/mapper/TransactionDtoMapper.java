package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.TransactionDto;
import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoMapper {

  public TransactionDto toDto(Transaction transaction) {
    if (transaction == null) return null;

    TransactionDto dto = new TransactionDto();
    dto.setId(transaction.getId());
    dto.setUserEmail(
        transaction.getUser() != null ? transaction.getUser().getEmailAddress() : null);
    dto.setCategoryName(
        transaction.getCategory() != null ? transaction.getCategory().getCategoryName() : null);
    dto.setTransactionDateTime(transaction.getTransactionDateTime());
    dto.setDescription(transaction.getDescription());
    dto.setAmount(transaction.getAmount());
    return dto;
  }

  public Transaction toEntity(TransactionDto dto, User user, TransactionCategory category) {
    if (dto == null) return null;

    Transaction transaction = new Transaction();
    transaction.setId(dto.getId());
    transaction.setUser(user);
    transaction.setCategory(category);
    transaction.setTransactionDateTime(dto.getTransactionDateTime());
    transaction.setDescription(dto.getDescription());
    transaction.setAmount(dto.getAmount());
    return transaction;
  }
}
