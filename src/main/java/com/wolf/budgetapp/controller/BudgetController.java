package com.wolf.budgetapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wolf.budgetapp.dto.BudgetDto;
import com.wolf.budgetapp.mapper.BudgetDtoMapper;
import com.wolf.budgetapp.model.Budget;
import com.wolf.budgetapp.service.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/budget", produces = MediaType.APPLICATION_JSON_VALUE)
public class BudgetController {
    
    private final BudgetService budgetService;
    private final BudgetDtoMapper budgetDtoMapper;

    // ALL Budgets (GET)
    @GetMapping
    ResponseEntity<List<BudgetDto>> getBudgets() {
    return new ResponseEntity<>(
        budgetDtoMapper.toDtoList(budgetService.getBudgets()), HttpStatus.OK);
  } 

    // Search by User Email (GET)
    @GetMapping(path = "/{email}")
    ResponseEntity<BudgetDto> getBudgetByEmail(@PathVariable("email") String email) {
    return new ResponseEntity<>(
        budgetDtoMapper.toDto(budgetService.getBudgetByEmail(email)), HttpStatus.OK);
  }

    // Search by Email, then by Category Name (GET)
    @GetMapping(path = "/{email}/{name}")
    ResponseEntity<BudgetDto> getBudgetByEmailAndName(@PathVariable("email") String email, @PathVariable("name") String name) {
        return new ResponseEntity<>(
            budgetDtoMapper.toDto(budgetService.getBudgetByEmailAndName(email, name)), HttpStatus.OK);
    }

    // Search by Email, then by Category Name, then by Budget Period (GET, exact budget)
    @GetMapping(path = "/{email}/{name}/{period}")
    ResponseEntity<BudgetDto> getExactBudget(@PathVariable("email") String email, @PathVariable("name") String name, @PathVariable("period") LocalDate period) {
        return new ResponseEntity<>(
        budgetDtoMapper.toDto(budgetService.getExactBudget(email, name, period)), HttpStatus.OK);
    }

    // Add new Budget (POST)
    @PostMapping(path = "/addBudget")
  ResponseEntity<Object> addBudget(@RequestBody BudgetDto budgetDto) {
    Budget budget;
    try {
      budget = budgetService.addBudget(budgetDto);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }

    // Edit Specific Budget (PUT)
    @PutMapping(path = "/{email}/{name}/{period}")
      ResponseEntity<Object> updateBudget(
      @PathVariable("email") String email, @PathVariable("name") String name, @PathVariable("period") LocalDate period, 
      @RequestBody BudgetDto budgetDto) {
    try {
      // Update Budget via Service, return OK status if successful
      Budget updatedBudget =
          budgetService.updateBudget(email, name, period, budgetDto);
      return new ResponseEntity<>(budgetDtoMapper.toDto(updatedBudget), HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
  }

    // Delete Specific Budget (DELETE)
    @DeleteMapping("/{email}/{name}/{period}")
    public ResponseEntity<String> deleteBudget(@PathVariable("email") String email, @PathVariable("name") String name, @PathVariable("period") LocalDate period) {
        try {
            budgetService.deleteBudget(email, name, period);
            return new ResponseEntity<>("Budget deleted successfully.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Budget not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
