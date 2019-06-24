package com.finance.homework.testHelperClasses;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.model.LoanEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanTestDataFactory {

    public static LoanEntity newLoanEntity() {
        return LoanEntity.builder().id(1L).loanAmount(new BigDecimal(100000.0)).debt(new BigDecimal(111111.0))
                .loanTerm(100).ipAddress("111.222.111.222").status(LoanStatus.MANUAL_CHECK).build();
    }

    public static LoanRequest newLoanRequest() {
        return LoanRequest.builder().user_pk(1L).loanAmount(new BigDecimal(100000.0)).loanTerm(250).ipAddress("111.222.111.222").build();
    }

    public static List<LoanEntity> makeLoanList() {

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
