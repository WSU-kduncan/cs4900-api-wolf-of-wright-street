package com.wolf.budgetapp.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {

  private BudgetIdDto id;
  // private String userEmail;
  // private String categoryName;
  private BigDecimal amount;
}
