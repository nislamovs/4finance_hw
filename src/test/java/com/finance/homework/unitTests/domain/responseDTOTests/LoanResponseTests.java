package com.finance.homework.unitTests.domain.responseDTOTests;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanResponseTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        LoanResponse loanResponse = new LoanResponse();
        assertThat(loanResponse, notNullValue());
        assertTrue(hasFieldsWithNullValues(loanResponse, Arrays.asList("loanAmount", "loanTerm", "ipAddress")));
    }

    @Test
    public void shouldBeEqual() {
        LoanResponse loanResponse1 = presetLoanResponse();
        LoanResponse loanResponse2 = presetLoanResponse();

        assertTrue(loanResponse1.equals(loanResponse2));

        loanResponse1 = loanResponse2;
        assertEquals(loanResponse1.hashCode(), loanResponse2.hashCode());
        assertEquals((new LoanRequest()).hashCode(), (new LoanRequest()).hashCode());

        loanResponse1 = presetLoanResponse_constructor();
        assertEquals(loanResponse1.hashCode(), loanResponse2.hashCode());
        assertEquals((new LoanRequest()).hashCode(), (new LoanRequest()).hashCode());
    }

    private LoanResponse presetLoanResponse() {
        LoanResponse loanResponse = new LoanResponse();
        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        loanResponse.setId(1L);
        loanResponse.setLoanAmount(new BigDecimal(100000));
        loanResponse.setLoanTerm(100);
        loanResponse.setDebt(new BigDecimal(1000000));
        loanResponse.setStatus(LoanStatus.MANUAL_CHECK.getStatus());
        loanResponse.setLoanExtentions(null);
        loanResponse.setCreatedDate(dateTime);
        loanResponse.setModifiedDate(dateTime);

        return loanResponse;
    }

    private LoanResponse presetLoanResponse_constructor() {

        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new LoanResponse(1L, new BigDecimal(100000), 100, LoanStatus.MANUAL_CHECK.getStatus(),
                new BigDecimal(1000000), null, dateTime, dateTime);
    }
}
