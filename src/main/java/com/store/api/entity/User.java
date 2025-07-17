package com.store.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor

@Builder
@AllArgsConstructor
@Table(schema = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


}
