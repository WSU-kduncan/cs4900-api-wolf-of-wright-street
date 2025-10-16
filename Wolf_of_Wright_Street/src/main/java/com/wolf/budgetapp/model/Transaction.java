package com.wolf.budgetapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    Long transactionId;

    @Column(name = "email_address", nullable = false, length = 255)
    String emailAddress;

    @Column(name = "category_name", nullable = false, length = 20)
    String categoryName;

    @Column(name = "transaction_date", nullable = false)
    LocalDateTime transactionDate;

    @Column(name = "transaction_description", nullable = false, length = 255)
    String transactionDescription;

    @Column(name = "transaction_amount", nullable = false, precision = 14, scale = 4)
    BigDecimal transactionAmouont;

    @ManyToOne
    @JoinColumn(name = "email_address", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_name", insertable = false, updatable = false)
    private TransactionCategory transactionCategory;
}
