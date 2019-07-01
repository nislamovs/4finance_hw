package com.finance.homework.unitTests.domain.exceptionTests;

import com.finance.homework.domain.exceptions.InvalidStatusException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidStatusExceptionTests {

    @Test
    public void shouldThrowException() {
        try {
            throw new InvalidStatusException("test123", new Exception("test123"));
        } catch(Exception e) {
            assertEquals("test123", e.getMessage());
        }
    }
}
