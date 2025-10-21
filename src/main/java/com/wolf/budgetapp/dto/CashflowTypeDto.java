package com.wolf.budgetapp.dto;

import com.wolf.budgetapp.model.TransactionCategory;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class CashflowTypeDto {
  String cashflowName;

  String cashflowDescription;

  Byte factor;

  // Is this needed?
  List<TransactionCategory> categories;
}
