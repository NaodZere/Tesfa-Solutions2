package com.asm.tesfaeribank.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JWTAuthEntryPoint.class})
@ExtendWith(SpringExtension.class)
class JWTAuthEntryPointTest {
    @Autowired
    private JWTAuthEntryPoint jWTAuthEntryPoint;


    @Test
    void testCommence() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponseWrapper response = mock(HttpServletResponseWrapper.class);
        doNothing().when(response).sendError(anyInt(), Mockito.<String>any());
        jWTAuthEntryPoint.commence(request, response, new AccountExpiredException("Msg"));
        verify(response).sendError(anyInt(), Mockito.<String>any());
        assertTrue(request.isRequestedSessionIdValid());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isAsyncStarted());
        assertTrue(request.isActive());
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getServletPath());
        assertEquals("localhost", request.getLocalName());
        assertEquals(80, request.getLocalPort());
        assertEquals("", request.getRequestURI());
        assertEquals("", request.getContextPath());
        assertEquals("http", request.getScheme());
        assertEquals("localhost", request.getServerName());
        assertEquals(80, request.getServerPort());
        assertEquals("", request.getMethod());
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertEquals("HTTP/1.1", request.getProtocol());
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertEquals("localhost", request.getRemoteHost());
        assertEquals(80, request.getRemotePort());
    }
}

