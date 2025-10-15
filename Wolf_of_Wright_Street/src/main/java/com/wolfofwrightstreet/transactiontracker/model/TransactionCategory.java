package com.wolfofwrightstreet.transactiontracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION_CATEGORY")
public class TransactionCategory {
    
    @Id
    @Column(name = "category_name", length = 20, nullable = false)
    String categoryName;

    @JoinColumn(name = "cashflow_type_name", nullable = false)
    @ManyToOne
    CashflowType cashflowType;

    @Column(name = "category_description", length = 255, nullable = true)
    String categoryDescription;
    
}
