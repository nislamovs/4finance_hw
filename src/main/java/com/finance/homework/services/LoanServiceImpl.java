package com.finance.homework.services;

import com.finance.homework.converters.LoanConverter;
import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.model.UserEntity;
import com.finance.homework.repository.LoanRepository;
import com.finance.homework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${loan.interest.normal}")
    BigDecimal yearInterest;

    @Value("${loan.maximum}")
    BigDecimal loanMaximum;

    @Override
    public List<LoanEntity> findAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public LoanEntity createLoan(LoanRequest loanRequest) throws UserNotFoundException {

        log.info("Creating new loan [request]: {}", loanRequest);

        if (!userRepository.existsUserEntityById(loanRequest.getUser_pk()))
            throw new UserNotFoundException("User with id {" + loanRequest.getUser_pk() + "} not found.");

        LoanEntity newLoan = LoanConverter.toEntity(loanRequest);

        newLoan.setStatus(LoanStatus.PENDING);
        newLoan.setDebt(BigDecimal.ZERO);
        newLoan.setUserEntity(UserEntity.builder().id(loanRequest.getUser_pk()).build());

        log.info("Creating new loan [entity]: {}", newLoan);

        return loanRepository.save(newLoan);
    }

    @Override
    public LoanEntity getLoanById(String loanId) throws LoanNotFoundException {
        Optional<LoanEntity> loan = loanRepository.findById(Long.valueOf(loanId));
        loan.orElseThrow(() -> new LoanNotFoundException("Loan with id [" + loanId + "] not found."));

        return loan.get();
    }

    @Override
    public LoanEntity updateStatus(String loanId, String status) throws LoanNotFoundException, InvalidStatusException {
        if (!loanRepository.existsLoanEntityById(Long.valueOf(loanId)))
            throw new LoanNotFoundException("Loan with id {" + loanId + "} not found.");

        if (StringUtils.isBlank(status) || !EnumUtils.isValidEnum(LoanStatus.class, status.toUpperCase()))
            throw new InvalidStatusException("Invalid loan status {" + status + "} .");

        LoanEntity loan = loanRepository.getOne(Long.valueOf(loanId));
        loan.setStatus(LoanStatus.valueOf(status.toUpperCase()));

        return loanRepository.save(loan);
    }

    @Override
    public int manageMidnightMaximalLoanAmounts(BigDecimal loanAmount) {
        return loanRepository.processBigLoans(LoanStatus.MANUAL_CHECK.getStatus(), loanAmount);
    }

    @Override
    public BigDecimal calculateDebt(BigDecimal loanAmount, int loanTerm) {

//        Basic Debt = loanAmount * (1 + (4% / 365 * loanTerm) ))

        BigDecimal debt = yearInterest;
        debt = debt.multiply(new BigDecimal(loanTerm));
        debt = debt.divide(new BigDecimal(365), 10, RoundingMode.CEILING);
        debt = debt.add(BigDecimal.ONE);
        debt = debt.multiply(loanAmount);
        debt = debt.setScale(2, RoundingMode.CEILING);

        return debt;
    }

    @Override
    public int processLoans() {
        List<LoanEntity> unprocessedLoans = loanRepository.getUnprocessedLoans(loanMaximum);
        for (LoanEntity loan : unprocessedLoans) {
            loan.setDebt(calculateDebt(loan.getLoanAmount(), loan.getLoanTerm()));
            loan.setStatus(LoanStatus.APPROVED);
        }

        loanRepository.saveAll(unprocessedLoans);

        return unprocessedLoans.size();
    }
}
