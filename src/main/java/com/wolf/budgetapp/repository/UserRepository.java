package com.wolf.budgetapp.repository;

import com.wolf.budgetapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  // findAll() returns list
  Optional<User> findByEmailAddress(String emailAddess);

  List<User> findByLastName(String lastName);
}
