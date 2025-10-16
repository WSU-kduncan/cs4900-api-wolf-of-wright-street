package com.wolfofwrightstreet.transactiontracker.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    Long transactionId;

    @JoinColumn(name = "email_address",  nullable = false)
    @ManyToOne
    User user;

    @JoinColumn(name = "category_name", nullable = false)
    @ManyToOne
    TransactionCategory category;

    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    Instant dateAdded;

    @Column(name = "transaction_description", nullable = true)
    String transactionDescription;

    @Column(name = "transaction_amount", precision = 14, scale = 4, nullable = false)
    BigDecimal transactionAmount;
}
