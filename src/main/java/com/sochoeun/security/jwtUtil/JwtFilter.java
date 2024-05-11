package com.sochoeun.security.jwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
public class JwtFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        // for mapper http request with our class LoginRequest
        ObjectMapper mapper = new ObjectMapper();
        try {
           LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
           Authentication authentication =
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   );
           // check has user or not (use in memory or database) and go get username and password
            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // after the successful or unsuccessful
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String secretKey = "dofwsjfoekskdfjowjlksdjfaweosdlf99djfljsdafo392jsdfjaoweifkasljfwe0";
        // if success will generate jwt token
        String token = Jwts.builder()
                .setSubject(authResult.getName()) // who log in
                .setIssuedAt(new Date())
                .claim("authorities", authResult.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .setIssuer("backendapi.com")
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();// compact will return -> string

        // response Header and add Bearer
        response.setHeader("Authorization","Bearer " + token);
    }
}
