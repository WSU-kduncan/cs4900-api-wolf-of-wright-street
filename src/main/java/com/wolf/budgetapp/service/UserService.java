package com.wolf.budgetapp.service;

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
    List<User> users = userRepository.findByLastName(lastName);
    if (users.isEmpty()) {
      throw new EntityNotFoundException("No users found with last name (" + lastName + ")");
    }
    return users;
  }
}
