package com.finance.homework.controllers;

import com.finance.homework.controllers.documentation.LoanControllerDoc;
import com.finance.homework.converters.LoanConverter;
import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;
import com.finance.homework.services.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/loan")
public class LoanController implements LoanControllerDoc {


    @Autowired
    LoanService loanService;

    @GetMapping(value = "/test")
    public void test() {

        log.info("test   test   test   test   test   test   test   test   test   ");

        loanService.processLoans();
//        loanService.calculateDebt(new BigDecimal(100000), 100);


//        return new ResponseEntity<Integer>(userService.blockUsersByLoanRequestCount(3), HttpStatus.OK);
//        return new ResponseEntity<Integer>(loanService.manageMidnightMaximalLoanAmounts(BigDecimal.valueOf(500000)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoans() {

        log.info("Retrieving loan list for all users.");

        return new ResponseEntity<List<LoanResponse>>(LoanConverter.toResponse(loanService.findAllLoans()), HttpStatus.OK);
    }

    @GetMapping(value = "/{loanId}")
    public ResponseEntity<?> getLoanById(@PathVariable String loanId) throws LoanNotFoundException {

        log.info("Retrieving loan by id {}.", loanId);

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.getLoanById(loanId)), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanRequest loanRequest, HttpServletRequest request) throws UserNotFoundException {

        log.info("Extending loan : {}", loanRequest);

        loanRequest.setIpAddress(request.getRemoteAddr());

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.createLoan(loanRequest)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{loanId}")
    @Transactional
    public ResponseEntity<?> changeLoanStatus(@PathVariable String loanId,
                                              @RequestParam("status") LoanStatus status) throws LoanNotFoundException, InvalidStatusException {

        log.info("Changing loan id:{} status to: {}", loanId, status.toString());

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.updateStatus(loanId, status.toString())), HttpStatus.ACCEPTED);
    }
}
