package com.finance.homework.domain.modelTests;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.finance.homework.model.BaseEntity;
import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.model.LoanEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BaseEntityTests {

    //Temporary solution

    @Mock
    BaseEntity baseEntity;

    @Test
    public void shouldMatchObjectValuesAndStringContent() {
        assertThat(baseEntity, notNullValue());
        baseEntity.setCreatedDate(null);
        baseEntity.setLastModifiedDate(null);
        assertTrue(hasFieldsWithNullValues(baseEntity, Arrays.asList("createdDate")));
    }
//
    @Test
    public void shouldBeEqual() {
        BaseEntity bEntity1 = baseEntity;
        BaseEntity bEntity2 = baseEntity;

        assertTrue(bEntity1.equals(bEntity2));
        assertEquals(bEntity1.hashCode(), bEntity2.hashCode());
        assertEquals(bEntity1.getCreatedDate(), bEntity2.getCreatedDate());
        assertEquals(bEntity1.getLastModifiedDate(), bEntity2.getLastModifiedDate());

        bEntity1 = bEntity2;
        assertEquals(bEntity1.hashCode(), bEntity2.hashCode());
        assertEquals((new ExtentionEntity()).hashCode(), (new ExtentionEntity()).hashCode());

//        bEntity1 = presetBaseEntity_Builder();
        assertEquals((new ExtentionEntity()).hashCode(), (new ExtentionEntity()).hashCode());

    }

    private BaseEntity presetBaseEntity_Manual() {
        BaseEntity bEntity = baseEntity;

        bEntity.setLastModifiedDate(null);
        bEntity.setCreatedDate(null);

        return bEntity;
    }
//
    private ExtentionEntity presetBaseEntity_Builder() {

        ExtentionEntity bEntity = ExtentionEntity.builder().id(1L).extentionDays(100)
                .loanEntity(new LoanEntity()).build();

        bEntity.setLastModifiedDate(null);
        bEntity.setCreatedDate(null);

        return bEntity;
    }
}
