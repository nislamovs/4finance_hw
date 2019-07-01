package com.finance.homework.schedulers;

import com.finance.homework.services.LoanService;
import com.finance.homework.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LoanProcessing {

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 23 * * *")
    @Transactional
    public void processLoans() {
        log.info("Processing loans [status = pending, amount < 500000] ...");

        int result = loanService.processLoans();

        log.info("Done with result : {}", result);
    }
}
