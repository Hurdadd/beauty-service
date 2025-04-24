package com.store.api.DTO;

import com.store.api.enums.Gender;

public record CreateRequestDTO(
         Long id ,
         String name,
         String lastName,
         Integer age,
         Long nationalCode,
         Gender gender,
         Integer number,
         String typeOfBeautyService,
         String description
) {}
