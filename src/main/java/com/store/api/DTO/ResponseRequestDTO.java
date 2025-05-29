package com.store.api.DTO;

import com.store.api.enums.Gender;

import java.time.LocalDateTime;

public record ResponseRequestDTO(
        String name,
        String lastName,
        Integer age,
        String nationalCode,
        Gender gender,
        Long number,
        String typeOfBeautyService,
        String description,
        LocalDateTime createdAt
) {

}
