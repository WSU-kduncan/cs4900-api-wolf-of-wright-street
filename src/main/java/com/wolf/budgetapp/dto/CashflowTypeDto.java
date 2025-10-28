package com.wolf.budgetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
// @Value
@NoArgsConstructor
@AllArgsConstructor
public class CashflowTypeDto {
  String cashflowName;
  String cashflowDescription;
  Byte factor;

  // Is this needed?
  // List<TransactionCategory> categories;
}
