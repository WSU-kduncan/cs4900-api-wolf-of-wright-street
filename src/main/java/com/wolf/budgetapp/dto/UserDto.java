package com.wolf.budgetapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class UserDto {

  String emailAddress;

  String firstName;

  String lastName;
}
