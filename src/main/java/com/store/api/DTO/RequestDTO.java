package com.store.api.DTO;

import com.store.api.enums.Gender;

public record RequestDTO(
    String name,
    String lastName,
    Integer age,
    Gender gender,
    Integer number,
    String typeOfBeautyService
) {}
