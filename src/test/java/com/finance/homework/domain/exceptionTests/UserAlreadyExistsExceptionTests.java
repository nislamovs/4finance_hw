package com.finance.homework.domain.exceptionTests;

import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAlreadyExistsExceptionTests {

    @Test
    public void shouldThrowException() {
        try {
            throw new UserAlreadyExistsException("test123", new Exception("test123"));
        } catch(Exception e) {
            assertEquals("test123", e.getMessage());
        }
    }
}
