package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

  UserDto toDto(User user);

  List<UserDto> toDtoList(List<User> users);

  User toEntity(UserDto dto);
}
