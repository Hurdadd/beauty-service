package com.store.api.DTO.response;

import com.store.api.enums.Gender;

import java.time.LocalDateTime;

public record ResponseRequestDTO(
        Long id ,
        String firstName,
        String lastName,
        Integer age,
        String nationalCode,
        Gender gender,
        String number,
        String typeOfBeautyService,
        String description,
        LocalDateTime createdAt
) {

}
