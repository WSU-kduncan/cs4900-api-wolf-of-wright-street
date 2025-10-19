package com.wolf.budgetapp.service;

import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
    return userRepository
        .findByEmailAddress(email)
        .orElseThrow(() -> new EntityNotFoundException("Error: User (" + email + ") not found"));
  }

  public List<User> getUsersByLastName(String lastName) {
    List<User> users = userRepository.findByLastName(lastName);
    if (users.isEmpty()) {
      throw new EntityNotFoundException("No users found with last name (" + lastName + ")");
    }
    return users;
  }
}
