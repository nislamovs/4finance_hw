package com.finance.homework.services;

import com.finance.homework.domain.exceptions.ExtentionNotFoundException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.model.ExtentionEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ExtentionService {

    List<ExtentionEntity> findAllExtentions();

    ExtentionEntity createExtention(ExtentionRequest extentionRequest) throws LoanNotFoundException;

    ExtentionEntity getExtentionById(String extentionId) throws ExtentionNotFoundException;

    BigDecimal calculateAmountForExtention(BigDecimal loanAmount, int extentionTerm);
}
