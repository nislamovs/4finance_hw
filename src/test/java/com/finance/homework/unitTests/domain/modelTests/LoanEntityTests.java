package com.finance.homework.unitTests.domain.modelTests;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.model.UserEntity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanEntityTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        LoanEntity loanEntity = new LoanEntity();
        assertThat(loanEntity, notNullValue());
        assertTrue(hasFieldsWithNullValues(loanEntity, Arrays.asList("loanAmount")));
    }

    @Test
    public void shouldBeEqual() {
        LoanEntity loanEntity1 = presetLoanEntity_Manual();
        LoanEntity loanEntity2 = presetLoanEntity_Manual();

        assertTrue(loanEntity1.equals(loanEntity2));

        loanEntity1 = loanEntity2;
        assertEquals(loanEntity1.hashCode(), loanEntity2.hashCode());
        assertEquals((new LoanEntity()).hashCode(), (new LoanEntity()).hashCode());

        loanEntity1 = presetLoanEntity_Builder();
        assertEquals(loanEntity1.hashCode(), loanEntity2.hashCode());
        assertEquals((new LoanEntity()).hashCode(), (new LoanEntity()).hashCode());

    }

    private LoanEntity presetLoanEntity_Manual() {
        LoanEntity loanEntity = new LoanEntity();
        ExtentionEntity extentionEntity = new ExtentionEntity();
        List<ExtentionEntity> extentions = new ArrayList<>(Arrays.asList(extentionEntity));

        loanEntity.setId(1L);
        loanEntity.setDebt(new BigDecimal(100000));
        loanEntity.setStatus(LoanStatus.MANUAL_CHECK);
        loanEntity.setLoanAmount(new BigDecimal(100000));
        loanEntity.setLoanTerm(100);
        loanEntity.setExtentions(extentions);
        loanEntity.setIpAddress("111.111.111.111");
        loanEntity.setUserEntity(new UserEntity());

        return loanEntity;
    }

    private LoanEntity presetLoanEntity_Builder() {

        LoanEntity loanEntity = LoanEntity.builder().id(1L).debt(new BigDecimal(100000)).status(LoanStatus.MANUAL_CHECK)
                .loanAmount(new BigDecimal(100000)).loanTerm(100).ipAddress("111.111.111.111")
                .extentions(new ArrayList<>(Arrays.asList(new ExtentionEntity())))
                .userEntity(new UserEntity())
                .build();

        return loanEntity;
    }
}
