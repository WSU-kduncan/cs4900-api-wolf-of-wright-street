package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.CashflowTypeDto;
import com.wolf.budgetapp.mapper.CashflowTypeDtoMapper;
import com.wolf.budgetapp.service.CashflowTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cashflow_type", produces = MediaType.APPLICATION_JSON_VALUE)
public class CashflowTypeController {

  private final CashflowTypeService cashflowTypeService;
  private final CashflowTypeDtoMapper cashflowTypeDtoMapper;

  // All Cashflow Types
  @GetMapping
  ResponseEntity<List<CashflowTypeDto>> getCashflowTypes() {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDtoList(cashflowTypeService.getCashflowTypes()), HttpStatus.OK);
  }

  // Search by Name (also is the identifier)
  @GetMapping(path = "/{name}")
  ResponseEntity<CashflowTypeDto> getCashflowByName(@PathVariable("name") String name) {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDto(cashflowTypeService.getCashflowByName(name)), HttpStatus.OK);
  }

  // Search by Factor
  @GetMapping(path = "factor/{factor}")
  ResponseEntity<CashflowTypeDto> getCashflowByFactor(@PathVariable("factor") Byte factor) {
    return new ResponseEntity<>(
        cashflowTypeDtoMapper.toDto(cashflowTypeService.getCashflowByFactor(factor)),
        HttpStatus.OK);
  }
  
  @PutMapping("/{name}")
  public ResponseEntity<CashflowTypeDto> updateCashflowType(
      @PathVariable("name") String name,
      @RequestBody CashflowTypeDto cashflowTypeDto) {

    try {
      CashflowType updated = cashflowTypeService.updateCashflowTypeByName(name, cashflowTypeDto);
      return new ResponseEntity<>(cashflowTypeDtoMapper.toDto(updated), HttpStatus.OK);

    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
