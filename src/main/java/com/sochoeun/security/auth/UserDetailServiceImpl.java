package com.sochoeun.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserServiceFakeImpl userServiceFake;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userServiceFake.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
