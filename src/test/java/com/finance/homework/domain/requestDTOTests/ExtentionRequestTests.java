package com.finance.homework.domain.requestDTOTests;

import com.finance.homework.domain.requests.ExtentionRequest;
import org.junit.Test;

import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtentionRequestTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        ExtentionRequest extentionRequest = new ExtentionRequest();
        assertThat(extentionRequest, notNullValue());
        assertTrue(hasFieldsWithNullValues(extentionRequest, Arrays.asList("extentionDays")));
    }

    @Test
    public void shouldBeEqual() {
        ExtentionRequest extentionRequest1 = presetLoanExtentionRequest();
        ExtentionRequest extentionRequest2 = presetLoanExtentionRequest();

        assertTrue(extentionRequest1.equals(extentionRequest2));

        extentionRequest1 = extentionRequest2;
        assertEquals(extentionRequest1.hashCode(), extentionRequest2.hashCode());
        assertEquals((new ExtentionRequest()).hashCode(), (new ExtentionRequest()).hashCode());
    }

    private ExtentionRequest presetLoanExtentionRequest() {
        ExtentionRequest extentionRequest = new ExtentionRequest();

        extentionRequest.setExtentionDays(3);
        extentionRequest.setLoan_pk(1L);

        return extentionRequest;
    }
}
