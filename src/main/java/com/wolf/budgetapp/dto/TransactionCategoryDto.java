package com.wolf.budgetapp.dto;

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
