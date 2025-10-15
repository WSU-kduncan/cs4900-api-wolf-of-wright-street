package com.wolfofwrightstreet.transactiontracker.model;

import com.wolfofwrightstreet.transactiontracker.model.composite.BudgetId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BUDGET")
public class Budget {
    
    @EmbeddedId
    BudgetId id;

    @Column(name = "budget_amount", precision = 12, scale = 4, nullable = false)
    BigDecimal budgetAmount;
}
