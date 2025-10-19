package com.wolf.budgetapp.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

  private Long id;
  private String userEmail;
  private String categoryName;
  private Instant transactionDateTime;
  private String description;
  private BigDecimal amount;
}
