package com.finance.homework.services;

import com.finance.homework.converters.ExtentionConverter;
import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.exceptions.ExtentionNotFoundException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.model.LoanEntity;
import com.finance.homework.repository.ExtentionRepository;
import com.finance.homework.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("extentionService")
@Transactional
public class ExtentionServiceImpl implements ExtentionService {

    @Autowired
    ExtentionRepository extentionRepository;

    @Autowired
    LoanRepository loanRepository;

    @Value("${loan.interest.extended}")
    BigDecimal weekInterest;

    @Override
    public List<ExtentionEntity> findAllExtentions() {
        return extentionRepository.findAll();
    }

    @Override
    public ExtentionEntity createExtention(ExtentionRequest extentionRequest) throws LoanNotFoundException {
        if (!loanRepository.existsLoanEntityByIdAndStatus(extentionRequest.getLoan_pk(), LoanStatus.APPROVED))
            throw new LoanNotFoundException("Loan with id {" + extentionRequest.getLoan_pk() + "} not found or not approved.");

        ExtentionEntity newExtention = ExtentionConverter.toEntity(extentionRequest);
        newExtention.setLoanEntity(LoanEntity.builder().id(extentionRequest.getLoan_pk()).build());

        //debt recalculation

        LoanEntity loan = loanRepository.getOne(extentionRequest.getLoan_pk());
        BigDecimal totalDebt = calculateAmountForExtention(loan.getLoanAmount(), extentionRequest.getExtentionDays());
        totalDebt = totalDebt.add(loan.getDebt());
        loan.setDebt(totalDebt);
        loanRepository.save(loan);

        return extentionRepository.save(newExtention);
    }

    @Override
    public BigDecimal calculateAmountForExtention(BigDecimal loanAmount, int extentionTerm) {

//        Extended Debt = loanAmount * (1.5% / 7 * extentionTerm)

        BigDecimal extendedDebt = weekInterest;
        extendedDebt = extendedDebt.multiply(new BigDecimal(extentionTerm));
        extendedDebt = extendedDebt.divide(new BigDecimal(7), 10, RoundingMode.CEILING);
        extendedDebt = extendedDebt.multiply(loanAmount);
        extendedDebt = extendedDebt.setScale(2, RoundingMode.CEILING);

        return extendedDebt;
    }

    @Override
    public ExtentionEntity getExtentionById(String extentionId) throws ExtentionNotFoundException {
        Optional<ExtentionEntity> extention = extentionRepository.findById(Long.valueOf(extentionId));
        extention.orElseThrow(() -> new ExtentionNotFoundException("Extention with id [" + extentionId + "] not found."));

        return extention.get();
    }
}
