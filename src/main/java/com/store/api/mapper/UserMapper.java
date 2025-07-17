package com.store.api.mapper;


import com.store.api.DTO.create.CreateUserDTO;
import com.store.api.DTO.response.ResponseUserDTO;
import com.store.api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUserDTO createUserDTO);
    ResponseUserDTO toDTO(User user);
}
