package com.finance.homework.domain.responseDTOTests;

import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ErrorResponse;
import org.junit.Test;

import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorResponseTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        ErrorResponse errorResponse = new ErrorResponse("test error {}", "value");
        assertThat(errorResponse, notNullValue());
        errorResponse.setErrorText(null);
        assertTrue(hasFieldsWithNullValues(errorResponse, Arrays.asList("errorText")));
    }

    @Test
    public void shouldBeEqual() {
        ErrorResponse errorResponse1 = presetLoanExtention();
        ErrorResponse errorResponse2 = presetLoanExtention();

        assertTrue(errorResponse1.equals(errorResponse2));

        errorResponse1 = errorResponse2;
        assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        assertEquals((new ExtentionRequest()).hashCode(), (new ExtentionRequest()).hashCode());
    }

    private ErrorResponse presetLoanExtention() {
        ErrorResponse errorResponse = new ErrorResponse("test error", new Exception("exception text"));
        errorResponse.setErrorText("Test error");

        return errorResponse;
    }
}
