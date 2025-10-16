package com.wolf.budgetapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "CASHFLOW_TYPE")
public class CashflowType {
    @Id
    @Column(name = "cashflow_type_name", nullable = false, length = 7)
    String cashFlowTypeName;

    @Column(name = "cashflow_type_description", length = 255)
    String cashFlowTypeDescription;

    @Column(name = "cashflow_type_factor", nullable = false)
    Byte cashFlowTypeFactor;

    @OneToMany(mappedBy = "cashFlowType")
    private List<TransactionCategory> transactionCategories;
}
