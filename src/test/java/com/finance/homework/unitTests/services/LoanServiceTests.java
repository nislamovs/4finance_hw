package com.finance.homework.unitTests.services;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.repository.LoanRepository;
import com.finance.homework.repository.UserRepository;
import com.finance.homework.services.LoanServiceImpl;

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

import static com.finance.homework.testHelperClasses.LoanTestDataFactory.makeLoanList;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanEntity;
import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanRequest;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTests {

    @InjectMocks
    LoanServiceImpl loanService = new LoanServiceImpl();

    @Mock
    LoanRepository loanRepository;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldRetrieveAllLoans() {
        List<LoanEntity> loanEntities = makeLoanList();
        when(loanRepository.findAll()).thenReturn(loanEntities);
        List<LoanEntity> actualLoans = loanService.findAllLoans();

        for (int i = 0; i < loanEntities.size(); i++)
            assert(actualLoans.get(i).equals(loanEntities.get(i)));
    }

    @Test
    public void shouldCreateNewLoan() {
        LoanRequest loanRequest = newLoanRequest();
        LoanEntity newLoan = newLoanEntity();
        newLoan.setStatus(LoanStatus.PENDING);
        newLoan.setDebt(BigDecimal.ZERO);
        newLoan.setUserEntity(newUserEntity());

        when(userRepository.existsUserEntityById(1L)).thenReturn(true);
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(newLoan);
        LoanEntity actualLoan = loanService.createLoan(loanRequest);

        assertNotNull(actualLoan);
        assertEquals(actualLoan, newLoan);
    }

    @Test
    public void shouldRetrieveUserById() {
        LoanEntity loanEntity = newLoanEntity();
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loanEntity));
        LoanEntity actualLoan = loanService.getLoanById("1");

        assertEquals(actualLoan, loanEntity);
    }

    @Test
    public void shouldUpdateLoanStatusByUserId() {
        LoanEntity loanEntity = newLoanEntity();
        when(loanRepository.existsLoanEntityById(1L)).thenReturn(true);
        when(loanRepository.getOne(1L)).thenReturn(loanEntity);
        loanEntity.setStatus(LoanStatus.MANUAL_CHECK);
        when(loanRepository.save(loanEntity)).thenReturn(loanEntity);

        LoanEntity actualLoan = loanService.updateStatus("1", LoanStatus.MANUAL_CHECK.getStatus());

        assertEquals(actualLoan.getStatus(), loanEntity.getStatus());
    }

    @Test
    public void shouldCalculateDebt() {
        ReflectionTestUtils.setField(loanService, "yearInterest", new BigDecimal(0.04));
        BigDecimal result = loanService.calculateDebt(BigDecimal.valueOf(100000), 100);

        assertEquals(new BigDecimal(101095.90).setScale(2, RoundingMode.CEILING),
                result.setScale(2, RoundingMode.CEILING));
    }

    @Test
    public void shouldProcessLoans() {
        List<LoanEntity> unprocessedLoans = makeLoanList();
        ReflectionTestUtils.setField(loanService, "loanMaximum", new BigDecimal(500000));
        ReflectionTestUtils.setField(loanService, "yearInterest", new BigDecimal(0.04));
        when(loanRepository.getUnprocessedLoans(new BigDecimal(500000))).thenReturn(unprocessedLoans);
        int result = loanService.processLoans();

        assertEquals(result, unprocessedLoans.size());
    }

    @Test
    public void shouldManageMidnightMaximalLoanAmounts() {
        when(loanRepository.processBigLoans(LoanStatus.MANUAL_CHECK.getStatus(), new BigDecimal(500000))).thenReturn(50);
        int result = loanService.manageMidnightMaximalLoanAmounts(new BigDecimal(500000));

        assertEquals(result, 50);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionOnCreatingNewLoan() {
        when(userRepository.existsUserEntityById(1L)).thenReturn(false);
        loanService.createLoan(newLoanRequest());
    }

    @Test(expected = LoanNotFoundException.class)
    public void shouldThrowExceptionOnRetrievingLoanById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        loanService.getLoanById("1");
    }

    @Test(expected = LoanNotFoundException.class)
    public void shouldThrowExceptionOnUpdatingLoanStatus() {
        when(loanRepository.existsLoanEntityById(1L)).thenReturn(false);
        loanService.updateStatus("1", LoanStatus.APPROVED.getStatus());
    }

    @Test(expected = InvalidStatusException.class)
    public void shouldThrowExceptionOnUpdatingLoanStatus_BlankStatus() {
        when(loanRepository.existsLoanEntityById(1L)).thenReturn(true);
        loanService.updateStatus("1", "");
    }

    @Test(expected = InvalidStatusException.class)
    public void shouldThrowExceptionOnUpdatingLoanStatus_InvalidStatus() {
        when(loanRepository.existsLoanEntityById(1L)).thenReturn(true);
        loanService.updateStatus("1", "asdasadas");
    }
}
