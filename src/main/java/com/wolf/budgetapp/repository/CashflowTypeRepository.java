package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.CashflowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CashflowTypeRepository extends JpaRepository<CashflowType, String> {

    Optional<CashflowType> findByFactor(Byte factor);
}
