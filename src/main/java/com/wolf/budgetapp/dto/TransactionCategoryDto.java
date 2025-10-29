package com.wolf.budgetapp.dto;

import com.wolf.budgetapp.model.CashflowType;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class TransactionCategoryDto {

    private String categoryName;

    private String categoryDescription;

    private String cashflowName;
}
