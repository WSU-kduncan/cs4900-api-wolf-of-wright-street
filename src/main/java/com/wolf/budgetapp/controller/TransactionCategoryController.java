package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.mapper.TransactionCategoryDtoMapper;
import com.wolf.budgetapp.service.TransactionCategoryService;
import com.wolf.budgetapp.model.TransactionCategory;
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
@RequestMapping(path = "/transaction_category", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionCategoryController {


    private final TransactionCategoryDtoMapper transactionCategoryDtoMapper;
    
    private final TransactionCategoryService transactionCategoryService;

    // returns all existing transaction categories
    @GetMapping
    public ResponseEntity<List<TransactionCategoryDto>> getAllTransactionCategories() {
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDtoList(transactionCategoryService.getAllTransactionCategories()), HttpStatus.OK);   
    }

    // returns category associated with given categoryName
    @GetMapping("/{categoryName}")
    public ResponseEntity<TransactionCategoryDto> getTransactionCategoryByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDto(transactionCategoryService.getTransactionCategoryByName(categoryName)), HttpStatus.OK);
    }

    // returns category description associated with given categoryName as a String
    @GetMapping(value = "/{categoryName}/description", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDescriptionByCategoryName(@PathVariable String categoryName) {
        return new ResponseEntity<>(transactionCategoryService.getDescriptionByCategoryName(categoryName), HttpStatus.OK);
    }

    // adds new transaction category 
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> addTransactionCategory(@RequestBody TransactionCategoryDto transactionCategoryDto) {
        TransactionCategory transactionCategory;
        try {
            transactionCategory = transactionCategoryService.createTransactionCategory(transactionCategoryDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDto(transactionCategory), HttpStatus.OK);
    }
    
    // updates existing category associated with given categoryName
    @PutMapping(value = "/{categoryName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateTransactionCategory(@PathVariable String categoryName, @RequestBody TransactionCategoryDto updatedTransactionCategoryDto) {
        TransactionCategory transactionCategory;
        try {
            transactionCategory = transactionCategoryService.updateTransactionCategory(categoryName, updatedTransactionCategoryDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDto(transactionCategory), HttpStatus.OK);
    }

}
