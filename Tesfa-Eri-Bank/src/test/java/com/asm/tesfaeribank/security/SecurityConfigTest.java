package com.asm.tesfaeribank.security;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityConfigTest {

    @Mock
    private JWTAuthEntryPoint jwtAuthEntryPoint;

    @Mock
    private UserDetailsService userDetailsService;



    @Test
    public void testJWTFilter() {
        SecurityConfig securityConfig = new SecurityConfig();
        JWTFilter jwtFilter = securityConfig.jwtFilter();
        // Add assertions or further tests if needed
    }

    // Add more tests for other methods if necessary
}
