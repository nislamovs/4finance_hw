package com.finance.homework.unitTests.domain.exceptionTests;

import com.finance.homework.domain.exceptions.UserNotFoundException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserNotFoundExceptionTests {

    @Test
    public void shouldThrowException() {
        try {
            throw new UserNotFoundException("test123", new Exception("test123"));
        } catch(Exception e) {
            assertEquals("test123", e.getMessage());
        }
    }
}
