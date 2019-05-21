package com.finance.homework.services;

import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.model.LoanEntity;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    List<LoanEntity> findAllLoans();

    LoanEntity createLoan(LoanRequest loan) throws UserNotFoundException;

    LoanEntity getLoanById(String loanId) throws LoanNotFoundException;

    LoanEntity updateStatus(String loanId, String status) throws LoanNotFoundException, InvalidStatusException;

    int manageMidnightMaximalLoanAmounts(BigDecimal loanAmount);

    BigDecimal calculateDebt(BigDecimal loanAmount, int loanTerm);

    int processLoans();
}
