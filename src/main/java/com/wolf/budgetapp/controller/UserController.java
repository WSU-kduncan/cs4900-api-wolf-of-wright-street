package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.mapper.UserDtoMapper;
import com.wolf.budgetapp.service.UserService;
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
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
// consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;
  private final UserDtoMapper userDtoMapper;

  // all users
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return new ResponseEntity<>(userDtoMapper.toDtoList(userService.getAllUsers()), HttpStatus.OK);
  }

  // search by email
  @GetMapping("/{email}")
  public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
    return new ResponseEntity<>(
        userDtoMapper.toDto(userService.getUserByEmail(email)), HttpStatus.OK);
  }

  // search by last name
  @GetMapping("/lastname/{lastName}")
  public ResponseEntity<List<UserDto>> getUsersByLastName(@PathVariable String lastName) {
    return new ResponseEntity<>(
        userDtoMapper.toDtoList(userService.getUsersByLastName(lastName)), HttpStatus.OK);
  }
}
