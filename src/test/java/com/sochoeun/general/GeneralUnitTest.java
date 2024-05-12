package com.sochoeun.general;

import com.sochoeun.security.PasswordConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GeneralUnitTest {

    @Autowired
    private PasswordConfig passwordConfig;

    @Test
    public void generatePassword(){
        String encode = new BCryptPasswordEncoder().encode("12345");
        System.out.println(encode);

    }
}
