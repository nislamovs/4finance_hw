package com.finance.homework.unitTests.domain.responseDTOTests;

import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ExtentionResponse;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtentionResponseTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        ExtentionResponse extentionResponse = new ExtentionResponse();
        assertThat(extentionResponse, notNullValue());
        assertTrue(hasFieldsWithNullValues(extentionResponse, Arrays.asList("extentionDays")));
    }

    @Test
    public void shouldBeEqual() {
        ExtentionResponse extentionResponse1 = presetLoanExtention();
        ExtentionResponse extentionResponse2 = presetLoanExtention();

        assertTrue(extentionResponse1.equals(extentionResponse2));

        extentionResponse1 = extentionResponse2;
        assertEquals(extentionResponse1.hashCode(), extentionResponse2.hashCode());
        assertEquals((new ExtentionRequest()).hashCode(), (new ExtentionRequest()).hashCode());

        extentionResponse1 = presetLoanExtention_constructor();
        assertEquals(extentionResponse1.hashCode(), extentionResponse2.hashCode());
        assertEquals((new ExtentionRequest()).hashCode(), (new ExtentionRequest()).hashCode());
    }

    private ExtentionResponse presetLoanExtention() {
        ExtentionResponse extentionResponse = new ExtentionResponse();

        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        extentionResponse.setExtentionDays(3);
        extentionResponse.setId(1L);
        extentionResponse.setCreatedDate(dateTime);

        return extentionResponse;
    }

    private ExtentionResponse presetLoanExtention_constructor() {

        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new ExtentionResponse(1L, 3, dateTime);
    }
}
