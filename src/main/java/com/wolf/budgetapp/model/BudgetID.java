package com.wolf.budgetapp.model;

// import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Data;
import lombok.Builder;

@Data
@Embeddable
//@NoArgsConstructor
@Builder
public class BudgetID implements Serializable {

  @Column(name = "email_address", nullable = false)
  private String emailAddress;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(name = "budget_period", nullable = false)
  private LocalDate budgetPeriod;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BudgetID)) return false;
    BudgetID that = (BudgetID) o;
    return Objects.equals(emailAddress, that.emailAddress)
        && Objects.equals(categoryName, that.categoryName)
        && Objects.equals(budgetPeriod, that.budgetPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAddress, categoryName, budgetPeriod);
  }
}
