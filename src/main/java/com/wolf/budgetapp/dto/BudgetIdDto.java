package com.wolf.budgetapp.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetIdDto {
    
    private String userEmail;
    private String categoryName;
    private LocalDate budgetPeriod;
}
