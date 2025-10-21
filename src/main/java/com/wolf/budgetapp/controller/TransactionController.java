package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.mapper.TransactionDtoMapper;
import com.wolf.budgetapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(
    path = "/transaction",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

  private final TransactionDtoMapper transactionDtoMapper;
  private final TransactionService transactionService;
  // private final UserService userService; // For fetching user by ID
  // private final TransactionCategoryService categoryService; // For fetching category by ID
  // realized this is dependent on others to be completed
  // I am going to do user now instead.

}
