package com.asm.tesfaeribank.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ResponseDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new ResponseDto("ABC123")).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        ResponseDto responseDto = new ResponseDto("ABC123");
        assertTrue(responseDto.canEqual(new ResponseDto("ABC123")));
    }


    @Test
    void testConstructor() {
        ResponseDto actualResponseDto = new ResponseDto();
        actualResponseDto.setAccessToken("ABC123");
        String actualToStringResult = actualResponseDto.toString();
        assertEquals("ABC123", actualResponseDto.getAccessToken());
        assertEquals("ResponseDto(accessToken=ABC123)", actualToStringResult);
    }


    @Test
    void testConstructor2() {
        ResponseDto actualResponseDto = new ResponseDto("ABC123");
        actualResponseDto.setAccessToken("ABC123");
        String actualToStringResult = actualResponseDto.toString();
        assertEquals("ABC123", actualResponseDto.getAccessToken());
        assertEquals("ResponseDto(accessToken=ABC123)", actualToStringResult);
    }

    @Test
    void testEquals() {
        assertNotEquals(new ResponseDto("ABC123"), null);
        assertNotEquals(new ResponseDto("ABC123"), "Different type to ResponseDto");
    }


    @Test
    void testEquals2() {
        ResponseDto responseDto = new ResponseDto("ABC123");
        assertEquals(responseDto, responseDto);
        int expectedHashCodeResult = responseDto.hashCode();
        assertEquals(expectedHashCodeResult, responseDto.hashCode());
    }


    @Test
    void testEquals3() {
        ResponseDto responseDto = new ResponseDto("ABC123");
        ResponseDto responseDto2 = new ResponseDto("ABC123");
        assertEquals(responseDto, responseDto2);
        int expectedHashCodeResult = responseDto.hashCode();
        assertEquals(expectedHashCodeResult, responseDto2.hashCode());
    }


    @Test
    void testEquals4() {
        ResponseDto responseDto = new ResponseDto("ExampleToken");
        assertNotEquals(responseDto, new ResponseDto("ABC123"));
    }

    @Test
    void testEquals5() {
        ResponseDto responseDto = new ResponseDto(null);
        assertNotEquals(responseDto, new ResponseDto("ABC123"));
    }


    @Test
    void testEquals6() {
        ResponseDto responseDto = new ResponseDto(null);
        ResponseDto responseDto2 = new ResponseDto(null);
        assertEquals(responseDto, responseDto2);
        int expectedHashCodeResult = responseDto.hashCode();
        assertEquals(expectedHashCodeResult, responseDto2.hashCode());
    }
}

