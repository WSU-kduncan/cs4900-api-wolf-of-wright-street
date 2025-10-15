package com.wolfofwrightstreet.transactiontracker.model.composite;

import com.wolfofwrightstreet.transactiontracker.model.TransactionCategory;
import com.wolfofwrightstreet.transactiontracker.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class BudgetId implements Serializable {

    @JoinColumn(name = "email_address", nullable = false)
    @ManyToOne
    User user;

    @JoinColumn(name = "category_name", nullable = false)
    @ManyToOne
    TransactionCategory category;

    @Column(name = "budget_period", nullable = false)
    LocalDate budgetPeriod;
}
