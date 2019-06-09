package com.finance.homework.controller;

import com.finance.homework.controllers.LoanController;
import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.services.LoanService;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTests {

    @InjectMocks
    LoanController loanController = new LoanController();

    @Mock
    LoanService loanService;

    @Test
    public void shouldRetrieveAllLoans() {
        List<LoanEntity> loanList = makeLoanList();
        when(loanService.findAllLoans()).thenReturn(loanList);

        ResponseEntity<?> response = loanController.getAllLoans();
        List<LoanResponse> loanResponse = (List<LoanResponse>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanList.get(0).getStatus().toString().toLowerCase(), loanResponse.get(0).getStatus().toLowerCase());
        assertEquals(loanList.get(2).getDebt(), loanResponse.get(2).getDebt());
    }

    @Test
    public void shouldRetrieveLoanById() {
        LoanRequest loanRequest = newLoanRequest();
        LoanEntity loanEntity = newLoanEntity();
        when(loanService.getLoanById("1")).thenReturn(loanEntity);

        ResponseEntity<?> response = loanController.getLoanById("1");
        LoanResponse loanResponse = (LoanResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loanRequest.getLoanAmount(), loanResponse.getLoanAmount());
        assertEquals(loanEntity.getId(), loanResponse.getId());
    }

    @Test
    public void shouldCreateLoan() {
        LoanRequest loanRequest = newLoanRequest();
        LoanEntity loanEntity = newLoanEntity();
        HttpServletRequest httpReq = mock(HttpServletRequest.class);
        when(loanService.createLoan(loanRequest)).thenReturn(loanEntity);

        ResponseEntity<?> response = loanController.createLoan(loanRequest, httpReq);
        LoanResponse loanResponse = (LoanResponse) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertThat(loanEntity.getStatus().toString(), equalToIgnoringCase(loanResponse.getStatus()));
        assertEquals(loanEntity.getLoanAmount(), loanResponse.getLoanAmount());
        assertEquals(loanEntity.getId(), loanResponse.getId());
    }

    @Test
    public void shouldChangeLoanStatusById() {
        LoanEntity loanEntity = newLoanEntity();
        doReturn(loanEntity).when(loanService).updateStatus("1", LoanStatus.MANUAL_CHECK.getStatus());

        ResponseEntity<?> response = loanController.changeLoanStatus("1", LoanStatus.MANUAL_CHECK.getStatus());
        LoanResponse loanResponse = (LoanResponse) response.getBody();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(LoanStatus.MANUAL_CHECK.getStatus(), loanResponse.getStatus());
        assertEquals(loanEntity.getId(), loanResponse.getId());
    }

    private LoanEntity newLoanEntity() {
        return LoanEntity.builder().id(1L).loanAmount(new BigDecimal(100000.0)).debt(new BigDecimal(111111.0))
                .loanTerm(100).ipAddress("111.222.111.222").status(LoanStatus.MANUAL_CHECK).build();
    }

    private LoanRequest newLoanRequest() {
        return LoanRequest.builder().user_pk(1L).loanAmount(new BigDecimal(100000.0)).loanTerm(250).build();
    }

    private List<LoanEntity> makeLoanList() {

        List<LoanEntity> loanList = new ArrayList<>();
        loanList.add(LoanEntity.builder().id(1L).loanAmount(new BigDecimal(100000.0))
                .debt(new BigDecimal(101000.0)).status(LoanStatus.APPROVED).loanTerm(250).build());

        loanList.add(LoanEntity.builder().id(2L).loanAmount(new BigDecimal(200000.0))
                        .debt(new BigDecimal(201000.0)).status(LoanStatus.PENDING).loanTerm(150).build());

        loanList.add(LoanEntity.builder().id(3L).loanAmount(new BigDecimal(300000.0))
                .debt(new BigDecimal(301000.0)).status(LoanStatus.REJECTED).loanTerm(300).build());

        return loanList;
    }
}
