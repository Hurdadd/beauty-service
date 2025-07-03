package com.store.api.entity;

import com.store.api.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(name = "national_code", nullable = false, unique = true)
    private String nationalCode;
    private Gender gender;
    private String number;
    private String typeOfBeautyService;
    private String description;
    private Integer age;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;



}

