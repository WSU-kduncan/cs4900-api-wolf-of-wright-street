package com.wolf.budgetapp.mapper;

import com.wolf.budgetapp.dto.UserDto;
import com.wolf.budgetapp.model.User;
import com.wolf.budgetapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

// mapper anotation uses interface below to automatically map entities to dtos on build 
@Mapper(
    componentModel = "spring",
    uses = {StudentService.class}
)
public interface UserDtoMapper {

    // converts User DTOs into User entities
    User toEntity(StudentDto studentDto) throws EntityNotFoundException;

    // converts User entities into DTOs
    UserDto toDto(User user) throws EntityNotFoundException; 

    // Returns a list of UserDTOs given a list of User entities
    List<UserDto> toDtoList(List<User> userList) throws EntityNotFoundException;
}