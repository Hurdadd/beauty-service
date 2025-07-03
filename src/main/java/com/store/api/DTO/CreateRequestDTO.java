package com.store.api.DTO;

import com.store.api.enums.Gender;

import java.time.LocalDateTime;

public record CreateRequestDTO(
         String name,
         String lastName,
         Integer age,
         String nationalCode,
         Gender gender,
         String number,
         String typeOfBeautyService,
         String description
) {}
