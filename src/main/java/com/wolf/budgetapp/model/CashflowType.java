package com.wolf.budgetapp.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "CASHFLOW_TYPE")
public class CashflowType {

    @Id
    @Column(name = "cashflow_type_name", length = 7, nullable = false)
    private String name;

    @Column(name = "cashflow_type_description")
    private String description;

    @Column(name = "cashflow_type_factor", nullable = false)
    private Byte factor;

    @OneToMany(mappedBy = "cashflowType")
    private List<TransactionCategory> categories;

}

