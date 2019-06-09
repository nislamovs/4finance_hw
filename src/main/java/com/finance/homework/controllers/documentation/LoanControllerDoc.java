package com.finance.homework.controllers.documentation;

import com.finance.homework.domain.exceptions.InvalidStatusException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.ErrorResponse;
import com.finance.homework.domain.responses.LoanResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Api(tags = { "Loan management" },  description = "Api calls for loan operations")
public interface LoanControllerDoc {

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve all loans with extentions (anonymous).",
            value = "Get all loans",
            response = LoanResponse.class,
            responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All loans are retrieved.", response = LoanResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    ResponseEntity<?> getAllLoans();

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve loan with extentions by id.",
            value = "Get loan by id",
            response = LoanResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All loans are retrieved.", response = LoanResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Loan not found.", response = ErrorResponse.class)
    })
    ResponseEntity<?> getLoanById(@ApiParam(value = "Loan id", required = true, name = "Loan id") String loanId) throws LoanNotFoundException;

    @ApiOperation(
            httpMethod = "POST",
            notes = "Resource to create new loan",
            value = "Create new loan.",
            response = LoanResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Loan created.", response = LoanResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class)
    })
    ResponseEntity<?> createLoan(@ApiParam(value = "Loan object", required = true, name = "Loan request") LoanRequest loanRequest,
                                 HttpServletRequest request) throws UserNotFoundException;

    @ApiOperation(
            httpMethod = "PUT",
            notes = "Resource to update loan status",
            value = "Update loan status.",
            response = LoanResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Loan status updated.", response = LoanResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Loan not found.", response = ErrorResponse.class)
    })
    ResponseEntity<?> changeLoanStatus(@ApiParam(value = "Loan id", required = true, name = "Loan id", example = "3") String loanId,
                                       @ApiParam(value = "Loan status", required = true, name = "Loan new status", defaultValue = "MANUAL_CHECK",
                                               allowableValues = "PENDING, APPROVED, REJECTED, PAYED_OFF, SENT_TO_COLLECTION, MANUAL_CHECK", example = "REJECTED") String status)
            throws LoanNotFoundException, InvalidStatusException;
}
