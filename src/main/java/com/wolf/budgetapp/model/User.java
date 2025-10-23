package com.wolf.budgetapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "USER")
public class User {

  @Id
  @Column(name = "email_address", length = 255, nullable = false)
  private String emailAddress;

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;

  @OneToMany(mappedBy = "user")
  private List<Transaction> transactions;

  @OneToMany(mappedBy = "user")
  private List<Budget> budgets;
}
