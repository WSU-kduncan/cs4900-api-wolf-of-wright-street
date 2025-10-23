package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.TransactionCategoryDto;
import com.wolf.budgetapp.mapper.TransactionCategoryDtoMapper;
import com.wolf.budgetapp.service.TransactionCategoryService;
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
@RequestMapping(path = "/transaction_category", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionCategoryController {


    private final TransactionCategoryDtoMapper transactionCategoryDtoMapper;
    private final TransactionCategoryService transactionCategoryService;

    @GetMapping
    public ResponseEntity<List<TransactionCategoryDto>> getAllTransactionCategories() {
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDtoList(transactionCategoryService.getAllTransactionCategories()), HttpStatus.OK);   
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<TransactionCategoryDto> getTransactionCategoryByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(transactionCategoryDtoMapper.toDto(transactionCategoryService.getTransactionCategoryByName()), HttpStatus.OK);
    }

}
