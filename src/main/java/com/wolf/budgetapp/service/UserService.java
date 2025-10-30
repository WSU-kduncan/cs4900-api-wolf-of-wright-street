package com.wolf.budgetapp.service;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.mapper.UserDtoMapper;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserDtoMapper userDtoMapper;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserByEmail(String email) {
    Optional<User> result = userRepository.findById(email);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("User (" + email + ") not found");
    }
    return result.get();
  }

  public List<User> getUsersByLastName(String lastName) {
    Optional<List<User>> result = userRepository.findByLastName(lastName);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("User (" + lastName + ") not found");
    }
    return result.get();
  }

  // POST (CREATE)
  public User createUser(UserDto userDto) {
    // You can include validations here
    if (userDto.getEmailAddress() == null || userDto.getEmailAddress().isBlank()) {
      throw new IllegalArgumentException("Email is required");
    }

    var user = userDtoMapper.toEntity(userDto);
    return userRepository.saveAndFlush(user);
  }

  // PUT (UPDATE)
  public User updateUserByEmail(String email, UserDto dto) {
    if (dto.getEmailAddress() == null || dto.getEmailAddress().isBlank()) {
      throw new IllegalArgumentException("Email is required");
    }

    User existingUser = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

    // Update fields from DTO (ignoring relationships if needed)
    userDtoMapper.updateEntity(dto, existingUser);

    return userRepository.save(existingUser);
  }

  // delete
  public void deleteUserByEmail(String email) {
    User user = userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    userRepository.delete(user);
  }
}
