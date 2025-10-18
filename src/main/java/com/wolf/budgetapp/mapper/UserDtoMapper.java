package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.model.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

  public UserDto toDto(User user) {
    if (user == null) {
      return null;
    }

    UserDto dto = new UserDto();
    dto.setEmailAddress(user.getEmailAddress());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());
    return dto;
  }

  // 👇 This method is required for your controller line to work!
  public List<UserDto> toDtoList(List<User> users) {
    return users.stream().map(this::toDto).collect(Collectors.toList());
  }

  public User toEntity(UserDto dto) {
    if (dto == null) {
      return null;
    }

    User user = new User();
    user.setEmailAddress(dto.getEmailAddress());
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    return user;
  }
}
