package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.mapper.UserDtoMapper;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.service.UserService;
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

  // POST Section
  @PostMapping("/createUser")
  public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
    try {
      var createdUser = userService.createUser(userDto);
      return new ResponseEntity<>(userDtoMapper.toDto(createdUser), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // PUT (UPDATE)
  @PutMapping("/{email}")
  public ResponseEntity<UserDto> updateUser(
      @PathVariable String email, @RequestBody UserDto userDto) {

    try {
      // Update user via service
      User updatedUser = userService.updateUserByEmail(email, userDto);

      // Return updated DTO with 200 OK
      return new ResponseEntity<>(userDtoMapper.toDto(updatedUser), HttpStatus.OK);

    } catch (EntityNotFoundException e) {
      // User not found
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    } catch (IllegalArgumentException e) {
      // Invalid input
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
