package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.repository.CashflowTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CashflowTypeService {
    
    private final CashflowTypeRepository cashflowTypeRepository;

    public List<CashflowType> getCashflowTypes() {
        return cashflowTypeRepository.findAll();
    }

    public CashflowType getCashflowByName(String name) throws EntityNotFoundException {
        Optional<CashflowType> result = cashflowTypeRepository.findById(name);
        if(result.isEmpty()) {
            throw new EntityNotFoundException("Cashflow Type (" + name + ") not found");
        }
        return result.get();
    }
}
