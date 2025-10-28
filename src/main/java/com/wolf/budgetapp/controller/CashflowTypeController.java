package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.CashflowTypeDto;
import com.wolf.budgetapp.mapper.CashflowTypeDtoMapper;
import com.wolf.budgetapp.model.CashflowType;
import com.wolf.budgetapp.service.CashflowTypeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cashflow_type", produces = MediaType.APPLICATION_JSON_VALUE)
public class CashflowTypeController {

  private final CashflowTypeService cashflowTypeService;
  private final CashflowTypeDtoMapper cashflowTypeDtoMapper;

  // All Cashflow Types (GET)
  @GetMapping
  ResponseEntity<List<CashflowTypeDto>> getCashflowTypes() {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDtoList(cashflowTypeService.getCashflowTypes()), HttpStatus.OK);
  }

  // Search by Name (also is the identifier) (GET)
  @GetMapping(path = "/{name}")
  ResponseEntity<CashflowTypeDto> getCashflowByName(@PathVariable("name") String name) {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDto(cashflowTypeService.getCashflowByName(name)), HttpStatus.OK);
  }

  // Search by Factor (GET)
  @GetMapping(path = "factor/{factor}")
  ResponseEntity<CashflowTypeDto> getCashflowByFactor(@PathVariable("factor") Byte factor) {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDto(cashflowTypeService.getCashflowByFactor(factor)),
        HttpStatus.OK);
  }

  // Add new Cashflow Type (POST)
  @PostMapping(path = "/addCashflowType")
  ResponseEntity<Object> addCashflowType(@RequestBody CashflowTypeDto cashflowTypeDto) {
    CashflowType cashflowType;
    try {
      cashflowType = cashflowTypeService.addCashflowType(cashflowTypeDto);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(cashflowType, HttpStatus.OK);
  }

  // Edit Cashflow Type (PUT)
  @PutMapping(path = "/{name}")
  ResponseEntity<Object> updateCashflowType(
      @PathVariable("name") String name, @RequestBody CashflowTypeDto cashflowTypeDto) {
    try {
      // Update Cashflow Type via Service, return OK status if successful
      CashflowType updatedCashflowType =
          cashflowTypeService.updateCashflowTypeByName(name, cashflowTypeDto);
      return new ResponseEntity<>(cashflowTypeDtoMapper.toDto(updatedCashflowType), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
