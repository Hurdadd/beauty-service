package com.store.api.DTO.create;

import com.store.api.constant.RegexPatterns;
import com.store.api.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {


    private String firstName;
    private String lastName;
    @NotBlank
    @Pattern(regexp = RegexPatterns.PHONE_IRAN, message = "Phone number must be valid")
    private String number;
    @Email
    private String email;
    private Gender gender;
    private String nationalCode;
    @NotBlank
    @Size(min = 8, max = 64)
    @Pattern(regexp = RegexPatterns.STRONG_PASSWORD, message = "Password is too weak")
    private String password;

}
