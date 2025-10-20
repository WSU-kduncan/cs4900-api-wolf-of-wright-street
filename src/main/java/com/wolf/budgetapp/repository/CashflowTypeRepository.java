package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.CashflowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashflowTypeRepository extends JpaRepository<CashflowType, String> {}
