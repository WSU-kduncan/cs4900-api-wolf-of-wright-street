package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.dto.CashflowTypeDto;
import com.wolf.budgetapp.mapper.CashflowTypeDtoMapper;
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

  private final CashflowTypeDtoMapper cashflowTypeDtoMapper;

  public List<CashflowType> getCashflowTypes() {
    return cashflowTypeRepository.findAll();
  }

  public CashflowType getCashflowByName(String name) throws EntityNotFoundException {
    Optional<CashflowType> result = cashflowTypeRepository.findById(name);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Cashflow Type named " + name + " not found");
    }
    return result.get();
  }

  public CashflowType getCashflowByFactor(Byte factor) throws EntityNotFoundException {
    Optional<CashflowType> result = cashflowTypeRepository.findByFactor(factor);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Cashflow Type with factor " + factor + " not found");
    }
    return result.get();
  }


  // Add new Cashflow Type (POST)
  public CashflowType addCashflowType(CashflowTypeDto cashflowTypeDto)
    throws EntityNotFoundException {
      return cashflowTypeRepository.saveAndFlush(cashflowTypeDtoMapper.toEntity(cashflowTypeDto));
    }

  // Edit Existing Cashflow Type (PUT)
  public CashflowType updateCashflowTypeByName(String name, CashflowTypeDto cashflowTypeDto) 
    throws EntityNotFoundException, IllegalArgumentException {
      if (cashflowTypeDto.getCashflowName() == null || cashflowTypeDto.getCashflowName().isBlank()) {
        throw new IllegalArgumentException("Name is required!");
      }
      CashflowType existingCashflowType = cashflowTypeRepository.findById(name).orElseThrow(() -> new EntityNotFoundException("User not found with name: " + name));
      cashflowTypeDtoMapper.updateEntity(cashflowTypeDto, existingCashflowType);

      return cashflowTypeRepository.save(existingCashflowType);
  }
}
