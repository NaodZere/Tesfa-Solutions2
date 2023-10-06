package com.asm.tesfaeribank.security;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtGenerator.class})
@ExtendWith(SpringExtension.class)
class JwtGeneratorTest {
    @Autowired
    private JwtGenerator jwtGenerator;


    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {


        jwtGenerator.generateToken(null);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken2() {
        jwtGenerator.generateToken(new TestingAuthenticationToken("Principal", "Credentials"));
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsernameFromJWT() {

        jwtGenerator.getUsernameFromJWT("ABC123");
    }


    @Test
    void testValidateToken() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> jwtGenerator.validateToken("ABC123"));
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> jwtGenerator.validateToken(""));
    }
}

