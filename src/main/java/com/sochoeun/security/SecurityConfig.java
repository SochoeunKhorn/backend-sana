package com.sochoeun.security;

import com.sochoeun.security.auth.UserDetailServiceImpl;
import com.sochoeun.security.jwtUtil.JwtFilter;
import com.sochoeun.security.jwtUtil.TokenVerify;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordConfig passwordConfig;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize ->{
                        authorize
                                .anyRequest()
                                .authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .addFilter(new JwtFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new TokenVerify(),JwtFilter.class)
                .cors(Customizer.withDefaults())
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        return http.build();
    }
    // using UserServiceFakeImpl
    // authentication provider for provides UserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider getAuthenticationProvider = new DaoAuthenticationProvider();
        getAuthenticationProvider.setUserDetailsService(userDetailService);
        getAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        return  getAuthenticationProvider;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    /*
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
    }*/


}
