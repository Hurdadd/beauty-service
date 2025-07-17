package com.store.api.mapper;

import com.store.api.DTO.create.CreateRequestDTO;
import com.store.api.DTO.response.ResponseRequestDTO;
import com.store.api.entity.Request;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface RequestMapper {



    Request toEntity(CreateRequestDTO createRequestDTO);

    ResponseRequestDTO toDTO(Request request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRequestDTO(CreateRequestDTO createRequestDTO, @MappingTarget Request request);
}