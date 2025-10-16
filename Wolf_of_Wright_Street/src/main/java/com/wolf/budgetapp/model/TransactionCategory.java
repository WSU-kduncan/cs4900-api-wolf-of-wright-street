package com.wolf.budgetapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION_CATEGORY")
public class TransactionCategory {
    @Id
    @Column(name = "category_name", nullable = false, length = 20)
    String categoryName;

    @Id
    @Column(name = "cashflow_type_name", nullable = false, length = 7)
    String cashFlowTypeName;

    @Column(name = "category_description", length = 255)
    String categoryDescription;

    @ManyToOne
    @JoinColumn(name = "cashflow_type_name", insertable = false, updatable = false)
    private CashflowType cashFlowType;

    @OneToMany(mappedBy = "transactionCategory")
    private List<Transaction> transactions;
}
