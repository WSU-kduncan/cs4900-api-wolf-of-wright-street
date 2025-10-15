package com.wolfofwrightstreet.transactiontracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CASHFLOW_TYPE")
public class CashflowType {
    
    @Id
    @Column(name = "cashflow_type_name", length = 7, nullable = false)
    String cashflowTypeName;

    @Column(name = "cashflow_type_description", length = 255, nullable = true)
    String cashflowTypeDescription;

    @Column(name = "cashflow_type_factor", columnDefinition = "SMALLINT", nullable = false)
    Integer cashflowTypeFactor;
}
