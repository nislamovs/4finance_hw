package com.finance.homework.controllers;

import com.finance.homework.controllers.documentation.LoanControllerDoc;
import com.finance.homework.converters.LoanConverter;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;
import com.finance.homework.services.LoanService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<?> getAllLoans() {

        log.info("Retrieving loan list for all users.");
        return new ResponseEntity<List<LoanResponse>>(LoanConverter.toResponse(loanService.findAllLoans()), HttpStatus.OK);
    }

    @GetMapping(value = "/{loanId}")
    public ResponseEntity<?> getLoanById(@PathVariable String loanId) {

        log.info("Retrieving loan by id {}.", loanId);

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.getLoanById(loanId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanRequest loanRequest, HttpServletRequest request) {

        log.info("Extending loan : {}", loanRequest);

        loanRequest.setIpAddress(request.getRemoteAddr());

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.createLoan(loanRequest)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{loanId}")
    public ResponseEntity<?> changeLoanStatus(@PathVariable String loanId,
                                              @ApiParam(required = true, defaultValue = "MANUAL_CHECK",
                                                      allowableValues = "PENDING, APPROVED, REJECTED, PAYED_OFF, SENT_TO_COLLECTION, MANUAL_CHECK",
                                                      example = "REJECTED")  //Neccessary to put it here to make possible visualise available values
                                                                             //Swagger does not inherit annotations from interface (long term bug)
                                              @RequestParam("status") String status) {

        log.info("Changing loan id:{} status to: {}", loanId, status);

        return new ResponseEntity<LoanResponse>(LoanConverter.toResponse(loanService.updateStatus(loanId, status)), HttpStatus.ACCEPTED);
    }
}
