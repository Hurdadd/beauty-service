package com.store.api.entity;

import com.store.api.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(name = "national_code", nullable = false, unique = true)
    private String nationalCode;
    private Gender gender;
    private Long number;
    private String typeOfBeautyService;
    private String description;
    private Integer age;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
