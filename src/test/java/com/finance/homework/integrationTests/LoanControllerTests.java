package com.finance.homework.integrationTests;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.requests.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.finance.homework.testHelperClasses.LoanTestDataFactory.newLoanRequest;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("integration-test")
public class LoanControllerTests extends AbstractIntegrationTest {

    @Test
    @DisplayName("Retrieving all loans and extentions")
    public void shouldRetrieveAllLoans() {
        given().get("loan/").then().statusCode(HttpStatus.OK.value())
                .body("id", hasItem(12))
                .body("status", hasItem(equalToIgnoringCase(LoanStatus.PAYED_OFF.toString())))
                .body("loanAmount", hasItem(hasToString("12000.0")));
    }

    @Test
    @DisplayName("Creating new loan")
    public void shouldCreateNewLoan() {
        given().body(newLoanRequest()).when().post("loan/")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("loanAmount", equalTo(100000))
                .body("status", equalToIgnoringCase(LoanStatus.PENDING.toString()))
                .body("createdDate", notNullValue());
    }

    @Test
    @DisplayName("Retrieving loan data by id")
    public void shouldGetLoanById() {
        given().get("loan/{id}", 5)
                .then().statusCode(HttpStatus.OK.value())
                .body("loanAmount", hasToString("7600.0"))
                .body("status", equalToIgnoringCase(LoanStatus.REJECTED.toString()))
                .body("createdDate", notNullValue());
    }

    @Test
    @DisplayName("Modifying loan status by id")
    public void shouldModifyLoanStatusById() {
        given().queryParam("status", LoanStatus.SENT_TO_COLLECTION).
                put("loan/{id}", 5).then().statusCode(HttpStatus.ACCEPTED.value())
                .body("status", equalToIgnoringCase(LoanStatus.SENT_TO_COLLECTION.toString()))
                .body("modifiedDate", notNullValue());
    }

    @Test
    @DisplayName("Retrieving loan data by wrong id")
    public void shouldFailOnGetLoanByWrongId() {
        given().get("loan/{id}", 100500)
                .then().statusCode(HttpStatus.NOT_FOUND.value())
                .body("errorText", containsString("Loan with id [100500] not found."));
    }

    @Test
    @DisplayName("Creating new loan with empty fields")
    public void shouldFailOnCreateNewLoan_EmptyFields() {
        LoanRequest loan = new LoanRequest();

        given().body(loan).when().post("loan/")
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorText", hasItem("Loan term cannot be empty."))
                .body("errorText", hasItem("Loan amount cannot be empty."));
    }
}
