package com.finance.homework.schedulers;

import com.finance.homework.services.LoanService;
import com.finance.homework.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class RiskAnalysis {

    @Value("${loan.application.max_attempts_from_one_ip}")
    int attempts;

    @Value("${loan.maximum}")
    BigDecimal maxAmount;

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void checkForTooManyRequests() {
        log.info("Checking for too many requests from single ip...");

        int result = userService.blockUsersByLoanRequestCount(attempts);

        log.info("Done with result : {}", result);
    }

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void checkForCrazyAttempts() {
        log.info("Checking for the attempts to take loan after 00:00 with max possible amount...");

        int result = loanService.manageMidnightMaximalLoanAmounts(maxAmount);

        log.info("Done with result : {}", result);
    }
}
