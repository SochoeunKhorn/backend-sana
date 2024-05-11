package com.sochoeun.security;

import com.sochoeun.security.jwtUtil.JwtFilter;
import com.sochoeun.security.jwtUtil.TokenVerify;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordConfig passwordConfig;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilter(new JwtFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new TokenVerify(),JwtFilter.class)
                .authorizeHttpRequests(authorize ->{
                        authorize
                                .anyRequest()
                                .authenticated();
                });
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordConfig.passwordEncoder().encode("admin123"))
                .authorities(RoleEnum.ADMIN.getAuthorities())
                .build();
        UserDetails staff = User.builder()
                .username("staff")
                .password(passwordConfig.passwordEncoder().encode("staff123"))
                .roles(RoleEnum.STAFF.name())
                .build();
        return new InMemoryUserDetailsManager(admin, staff);
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
