package com.wolf.budgetapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class TransactionCategoryDto {

    // only expose necesarry api fields; entity relationships not necesarry at this level
    private String categoryName;

    private String categoryDescription;

    private String cashflowName;
}
