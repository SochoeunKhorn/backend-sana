package com.sochoeun.security.auth;

import com.sochoeun.entity.Permission;
import com.sochoeun.entity.Role;
import com.sochoeun.entity.User;
import com.sochoeun.exception.ApiException;
import com.sochoeun.repository.UserRepository;
import com.sochoeun.security.PasswordConfig;
import com.sochoeun.security.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    //private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;
    @Override
    public Optional<UserDetailImpl> findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User Not Found"));
        // mapper user to UserDetailImpl
        UserDetailImpl userDetail =
                UserDetailImpl.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(getAuthorities(user.getRoles()))
                        .accountNonExpired(user.isAccountNonExpired())
                        .accountNonLocked(user.isAccountNonLocked())
                        .credentialNonExpired(user.isAccountNonExpired())
                        .enable(user.isEnabled())
                        .build();
        return Optional.ofNullable(userDetail);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
        Set<SimpleGrantedAuthority> authorities = roles.stream()
                .flatMap(role -> getPermission(role))
                .collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> getRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        authorities.addAll(getRoles);
        return authorities;
    }

    private Stream<SimpleGrantedAuthority> getPermission(Role roles){
        return roles.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()));
    }

}
