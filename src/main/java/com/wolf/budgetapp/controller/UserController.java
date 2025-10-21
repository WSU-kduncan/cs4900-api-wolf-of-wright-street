package com.wolf.budgetapp.controller;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.service.UserService;
import com.wolf.budgetapp.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RequiredArgsConstructor
@RestController
// sets base endpoint to /user
@RequestMapping(
    path = "user",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserDtoMapper userDtoMapper;

    private final UserService userService;

    // hitting /user with no other path variables returns list of all UserDtos
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(
            userDtoMapper.toDtoList(userService.getAllUsers()), HttpStatus.OK);
    }

    // hitting endpoint /user/{email} returns UserDto associated with given email
    @GetMapping(path = "{email}")
    ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(
            userDtoMapper.toDto(userService.getUserByEmail(email)), HttpStatus.OK);
    }

    // hitting /user/lastname/{lastName} returns UserDto associated with given lastName
    @GetMapping(path = "lastname/{lastName}") 
    ResponseEntity<List<UserDto>> getUserBylastName(@PathVariable String lastName) {
        return new ResponseEntity<>(
            userDtoMapper.toDtoList(userService.getUserBylastName(lastName)), HttpStatus.OK);
    }
}
