package com.asm.tesfaeribank.security;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {JWTFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JWTFilterTest {
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTFilter jWTFilter;

    @MockBean
    private JwtGenerator jwtGenerator;


    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jWTFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }

}

