package com.wolf.budgetapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table(name = "BUDGET")
public class Budget {

    @Id
    @Column(name = "email_address", nullable = false, length = 255)
    String emailAddress;

    @Id
    @Column(name = "category_name", nullable = false, length = 20)
    String categoryName;

    @Column(name = "budget_period", nullable = false)
    LocalDate budgetPeriod;

    @Column(name = "budget_amount", nullable = false, precision = 14, scale = 4)
    BigDecimal budgetAmount;

    @ManyToOne
    @JoinColumn(name = "email_address", insertable = false, updatable = false)
    private User user;
}
