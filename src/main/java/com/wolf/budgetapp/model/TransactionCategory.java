package com.wolf.budgetapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "TRANSACTION_CATEGORY")
public class TransactionCategory {

  @Id
  @Column(name = "category_name", length = 20, nullable = false)
  private String categoryName;

  @Column(name = "category_description")
  private String categoryDescription;

  @ManyToOne
  @JoinColumn(name = "cashflow_type_name", nullable = false)
  private CashflowType cashflowType;

  @OneToMany(mappedBy = "category")
  private List<Transaction> transactions;

  @OneToMany(mappedBy = "category")
  private List<Budget> budgets;
}
