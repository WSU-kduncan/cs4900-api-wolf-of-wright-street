package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.TransactionCategory;
import com.wolf.budgetapp.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, String>{
    
}
