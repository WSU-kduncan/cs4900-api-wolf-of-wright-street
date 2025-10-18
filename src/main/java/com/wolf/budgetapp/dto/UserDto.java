package com.wolf.budgetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Value
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private String emailAddress;
  private String firstName;
  private String lastName;
}
