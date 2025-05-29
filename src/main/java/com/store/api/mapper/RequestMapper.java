package com.store.api.mapper;

import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.ResponseRequestDTO;
import com.store.api.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface RequestMapper {


    @Mapping(source = "nationalCode", target = "nationalCode")
    Request toEntity(CreateRequestDTO createRequestDTO);

    @Mapping(source = "nationalCode", target = "nationalCode")
    ResponseRequestDTO toDTO(Request request);
}