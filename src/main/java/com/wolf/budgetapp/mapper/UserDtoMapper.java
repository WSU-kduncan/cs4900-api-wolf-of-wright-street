package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.model.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

// @Mapper(componentModel = "spring")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

  UserDto toDto(User user);

  List<UserDto> toDtoList(List<User> users);

  User toEntity(UserDto dto);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "emailAddress", source = "emailAddress")
  @Mapping(target = "firstName", source = "firstName")
  @Mapping(target = "lastName", source = "lastName")
  void updateEntity(UserDto dto, @MappingTarget User entity);
}
