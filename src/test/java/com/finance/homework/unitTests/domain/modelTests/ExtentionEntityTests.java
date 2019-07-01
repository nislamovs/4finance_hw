package com.finance.homework.unitTests.domain.modelTests;

import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.model.LoanEntity;

import org.junit.Test;

import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExtentionEntityTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        ExtentionEntity extentionEntity = new ExtentionEntity();
        assertThat(extentionEntity, notNullValue());
        assertTrue(hasFieldsWithNullValues(extentionEntity, Arrays.asList("extentionDays")));
    }

    @Test
    public void shouldBeEqual() {
        ExtentionEntity extentionEntity1 = presetExtentionEntity_Manual();
        ExtentionEntity extentionEntity2 = presetExtentionEntity_Manual();

        assertTrue(extentionEntity1.equals(extentionEntity2));

        extentionEntity1 = extentionEntity2;
        assertEquals(extentionEntity1.hashCode(), extentionEntity2.hashCode());
        assertEquals((new ExtentionEntity()).hashCode(), (new ExtentionEntity()).hashCode());

        extentionEntity1 = presetExtentionEntity_Builder();
        assertEquals(extentionEntity1.hashCode(), extentionEntity2.hashCode());
    }

    private ExtentionEntity presetExtentionEntity_Manual() {
        ExtentionEntity extentionEntity = new ExtentionEntity();

        extentionEntity.setId(1L);
        extentionEntity.setExtentionDays(100);
        extentionEntity.setLoanEntity(new LoanEntity());

        return extentionEntity;
    }

    private ExtentionEntity presetExtentionEntity_Builder() {

        ExtentionEntity extentionEntity = ExtentionEntity.builder().id(1L).extentionDays(100)
                .loanEntity(new LoanEntity()).build();

        return extentionEntity;
    }
}
