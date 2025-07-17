package com.store.api.DTO.create;

import com.store.api.constant.RegexPatterns;
import com.store.api.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateRequestDTO(
         String name,
         String lastName,
         Integer age,
         String nationalCode,
         Gender gender,
         @NotBlank
         @Pattern(regexp = RegexPatterns.PHONE_IRAN, message = "Phone number must be valid")
          String number,
         String typeOfBeautyService,
         String description,
         Long userId
) {}
