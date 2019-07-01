package com.finance.homework.unitTests.domain.requestDTOTests;

import com.finance.homework.domain.requests.LoanRequest;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanRequestTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        LoanRequest loanRequest = new LoanRequest();
        assertThat(loanRequest, notNullValue());
        assertTrue(hasFieldsWithNullValues(loanRequest, Arrays.asList("loanAmount", "loanTerm", "ipAddress")));
    }

    @Test
    public void shouldBeEqual() {
        LoanRequest loanRequest1 = presetLoanRequest();
        LoanRequest loanRequest2 = presetLoanRequest();

        assertTrue(loanRequest1.equals(loanRequest2));

        loanRequest1 = loanRequest2;
        assertEquals(loanRequest1.hashCode(), loanRequest2.hashCode());
        assertEquals((new LoanRequest()).hashCode(), (new LoanRequest()).hashCode());
    }

    private LoanRequest presetLoanRequest() {
        LoanRequest loanRequest = new LoanRequest();

        loanRequest.setUser_pk(1L);
        loanRequest.setLoanAmount(new BigDecimal(100000));
        loanRequest.setLoanTerm(100);
        loanRequest.setIpAddress("111.222.111.111");

        return loanRequest;
    }
}
