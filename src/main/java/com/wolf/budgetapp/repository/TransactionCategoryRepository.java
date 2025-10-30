package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, String>{

}
