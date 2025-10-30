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
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "TRANSACTION")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "email_address", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "category_name", nullable = false)
  private TransactionCategory category;

  @Column(name = "transaction_date", nullable = false)
  private Instant transactionDateTime;

  @Column(name = "transaction_description")
  private String description;

  @Column(name = "transaction_amount", nullable = false, precision = 14, scale = 4)
  private BigDecimal amount;
}
