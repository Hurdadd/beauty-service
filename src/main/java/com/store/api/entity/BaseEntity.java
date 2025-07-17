package com.store.api.entity;

import com.store.api.enums.Gender;
import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "PhoneNumber", unique = true , nullable = false)
    private String number;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "national_code", nullable = false, unique = true)
    private String nationalCode;
    private Integer age;

}
