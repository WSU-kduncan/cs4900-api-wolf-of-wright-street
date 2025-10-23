package com.wolf.budgetapp.dto;

import com.wolf.budgetapp.model.Transaction;
import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.model.Budget;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class TransactionCategoryDto {

    private String categoryName;

    private String categoryDescription;

    private CashflowType cashFlowType;

    private List<Transaction> transactions;

    private List<Budget> budgets;
}
