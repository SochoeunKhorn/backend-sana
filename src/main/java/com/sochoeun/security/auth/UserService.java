package com.sochoeun.security.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<UserDetailImpl> findUserByUsername(String username);
}
