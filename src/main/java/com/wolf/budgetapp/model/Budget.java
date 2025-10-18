package com.wolf.budgetapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.wolf.budgetapp.model.BudgetID;


@Entity
@Table(name = "BUDGET")
public class Budget {
    // make composite key a class
    @EmbeddedId
    private BudgetID id;

    @ManyToOne
    @MapsId("emailAddress")
    @JoinColumn(name = "email_address", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("categoryName")
    @JoinColumn(name = "category_name", nullable = false)
    private TransactionCategory category;

    @Column(name = "budget_amount", nullable = false, precision = 14, scale = 4)
    private BigDecimal amount;

}

