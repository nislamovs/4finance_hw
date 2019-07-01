package com.finance.homework.unitTests.converters;

import com.finance.homework.converters.LoanConverter;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;
import com.finance.homework.model.LoanEntity;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.finance.homework.testHelperClasses.LoanTestDataFactory.makeLoanList;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanEntity;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanConverterTests {

    @Test
    public void shouldConvertLoanRequestToLoanEntity(){
        LoanRequest loanRequest = newLoanRequest();
        LoanEntity loanEntity = LoanConverter.toEntity(loanRequest);

        assertConvertedLoan(loanRequest, loanEntity);
    }

    @Test
    public void shouldConvertLoanEntityToLoanResponse(){
        LoanEntity loanEntity = newLoanEntity();
        LoanResponse loanResponse = LoanConverter.toResponse(loanEntity);

        assertConvertedLoan(loanEntity, loanResponse);
    }

    @Test
    public void shouldConvertLoanEntityListToLoanResponseList(){
        List<LoanEntity> loanEntities = makeLoanList();
        List<LoanResponse> loanResponses = LoanConverter.toResponse(loanEntities);

        for (int n = 0; n < loanEntities.size(); n++) {
            assertConvertedLoan(loanEntities.get(n), loanResponses.get(n));
        }
    }

    @Test
    public void shouldPresetMapperOnConstruct(){
        LoanConverter loanConv = new LoanConverter();
        ModelMapper testMapper = (ModelMapper) ReflectionTestUtils.getField(loanConv,"mapper");
        assertTrue(testMapper.getConfiguration().isFieldMatchingEnabled());
    }

    private void assertConvertedLoan(LoanRequest loanRequest, LoanEntity loanEntity) {
        assertEquals(loanRequest.getLoanAmount(),    loanEntity.getLoanAmount());
        assertEquals(loanRequest.getLoanTerm(),      loanEntity.getLoanTerm());
    }

    private void assertConvertedLoan(LoanEntity loanEntity, LoanResponse loanResponse) {
        assertEquals(loanEntity.getId(),                loanResponse.getId());
        assertEquals(loanEntity.getLoanAmount(),        loanResponse.getLoanAmount());
        assertEquals(loanEntity.getDebt(),              loanResponse.getDebt());
        assertThat(loanEntity.getStatus().toString(),   equalToIgnoringCase(loanResponse.getStatus()));
        assertEquals(loanEntity.getLoanTerm(),          loanResponse.getLoanTerm());
    }
}
