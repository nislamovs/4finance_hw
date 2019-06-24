package com.finance.homework.domain.enumTests;

import com.finance.homework.domain.enums.LoanStatus;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanStatusTests {

    @Test
    public void shouldReturnDefiniteValues() {
        assertEquals(new Integer(2), LoanStatus.APPROVED.getId());
        assertEquals("approved", LoanStatus.APPROVED.getStatus());
    }
}
