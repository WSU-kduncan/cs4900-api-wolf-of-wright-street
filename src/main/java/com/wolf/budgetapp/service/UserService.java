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

    // method calls on the repository layer which returns a list of all User entities from db
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

    public List<User> getUserBylastName(String lastName) {
        Optional<List<User>> result = userRepository.findByLastName(lastName);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User (" + lastName + ") not found");
        }
        return result.get();
    }
}