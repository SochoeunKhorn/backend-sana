package com.sochoeun.security.auth;

import com.sochoeun.security.PasswordConfig;
import com.sochoeun.security.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService{
    private final PasswordConfig passwordConfig;
    @Override
    public Optional<UserDetailImpl> findUserByUsername(String username) {
        // create fake user for find
        List<UserDetailImpl> users = List.of(
                new UserDetailImpl(
                        "admin",
                        passwordConfig.passwordEncoder().encode("123"),
                        RoleEnum.ADMIN.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new UserDetailImpl(
                        "staff",
                        passwordConfig.passwordEncoder().encode("123"),
                        RoleEnum.STAFF.getAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        // user by username
         return users.stream()
                .filter(name->name.getUsername().equals(username))
                .findFirst();
    }
}
