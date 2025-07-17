package com.store.api.entity;


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
@Table(schema = "requests")
public class Request extends BaseEntity {


    private String typeOfBeautyService;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private Long userId;



}

