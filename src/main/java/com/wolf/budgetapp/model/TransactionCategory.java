package com.wolf.budgetapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TRANSACTION_CATEGORY")
public class TransactionCategory {

    @Id
    @Column(name = "category_name", length = 20, nullable = false)
    private String name;

    @Column(name = "category_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "cashflow_type_name", nullable = false)
    private CashflowType cashflowType;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;

}

