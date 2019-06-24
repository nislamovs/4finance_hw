package com.finance.homework.services;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.exceptions.ExtentionNotFoundException;
import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.repository.ExtentionRepository;
import com.finance.homework.repository.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.makeExtentionList;
import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.newExtentionEntity;
import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.newExtentionRequest;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.makeLoanList;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanEntity;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanRequest;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtentionServiceTests {

    @InjectMocks
    ExtentionServiceImpl extentionService = new ExtentionServiceImpl();

    @Mock
    ExtentionRepository extentionRepository;

    @Mock
    LoanRepository loanRepository;

    @Test
    public void shouldRetrieveAllExtentions() {
        List<ExtentionEntity> extentionEntities = makeExtentionList();
        when(extentionRepository.findAll()).thenReturn(extentionEntities);
        List<ExtentionEntity> actualExtentions = extentionService.findAllExtentions();

        for (int i = 0; i < extentionEntities.size(); i++)
            assert(actualExtentions.get(i).equals(extentionEntities.get(i)));
    }

    @Test
    public void shouldCreateNewExtention() {
        ExtentionRequest extentionRequest = newExtentionRequest();
        ExtentionEntity newExtention = newExtentionEntity();
        LoanEntity loan = newLoanEntity();
        newExtention.setLoanEntity(loan);
        ReflectionTestUtils.setField(extentionService, "weekInterest", new BigDecimal(0.015));

        when(loanRepository.existsLoanEntityByIdAndStatus(1L, LoanStatus.APPROVED)).thenReturn(true);
        when(loanRepository.getOne(1L)).thenReturn(loan);
        when(extentionRepository.save(any(ExtentionEntity.class))).thenReturn(newExtention);

        ExtentionEntity actualExtention = extentionService.createExtention(extentionRequest);
        assertEquals(actualExtention.getExtentionDays(), newExtention.getExtentionDays());
    }

    @Test
    public void shouldRetrieveExtentionById() {
        ExtentionEntity extentionEntity = newExtentionEntity();
        when(extentionRepository.findById(1L)).thenReturn(Optional.of(extentionEntity));
        ExtentionEntity actualExtention = extentionService.getExtentionById("1");

        assertEquals(actualExtention, extentionEntity);
    }

    @Test
    public void shouldCalculateAmountForExtention() {

        ReflectionTestUtils.setField(extentionService, "weekInterest", new BigDecimal(0.015));
        BigDecimal result = extentionService.calculateAmountForExtention(new BigDecimal(101095.90), 100);

        assertEquals(new BigDecimal(21663.41).setScale(2, RoundingMode.CEILING),
                result.setScale(2, RoundingMode.CEILING));
    }

    @Test(expected = LoanNotFoundException.class)
    public void shouldThrowExceptionOnCreatingnewExtention() {
        when(loanRepository.existsLoanEntityById(1L)).thenReturn(false);
        extentionService.createExtention(newExtentionRequest());
    }

    @Test(expected = ExtentionNotFoundException.class)
    public void shouldThrowExceptionOnRetrievingExceptionById() {
        when(extentionRepository.findById(1L)).thenReturn(Optional.empty());
        extentionService.getExtentionById("1");
    }
}
