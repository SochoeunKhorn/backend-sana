package com.sochoeun.entity;

import com.sochoeun.security.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    //@ManyToMany(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private RoleEnum roles;
}
