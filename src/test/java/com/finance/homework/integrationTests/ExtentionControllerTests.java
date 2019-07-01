package com.finance.homework.integrationTests;

import com.finance.homework.domain.requests.ExtentionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.newExtentionRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExtentionControllerTests extends AbstractIntegrationTest {

    @Test
    @DisplayName("Retrieving all extentions")
    public void shouldRetrieveAllExtentions() {
        given().get("extention/").then().statusCode(HttpStatus.OK.value())
                .body("id", hasItem(12))
                .body("extentionDays", hasItem(300))
                .body("createdDate", notNullValue());
    }

    @Test
    @DisplayName("Creating new extention")
    public void shouldCreateNewExtention() {
        given().body(newExtentionRequest()).when().post("extention/")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("extentionDays", equalTo(100))
                .body("createdDate", notNullValue());
    }

    @Test
    @DisplayName("Retrieving extention data by id")
    public void shouldGetExtentionById() {
        given().get("extention/{id}", 5)
                .then().statusCode(HttpStatus.OK.value())
                .body("id", equalTo(5))
                .body("extentionDays", equalTo(30))
                .body("createdDate", notNullValue());
    }

    @Test
    @DisplayName("Retrieving extention data by wrong id")
    public void shouldFailOnGetExtentionByWrongId() {
        given().get("extention/{id}", 100500)
                .then().statusCode(HttpStatus.NOT_FOUND.value())
                .body("errorText", containsString("Extention with id [100500] not found."));
    }

    @Test
    @DisplayName("Creating new extention with empty fields")
    public void shouldFailOnCreateNewExtention_EmptyFields() {
        ExtentionRequest extentions = new ExtentionRequest();

        given().body(extentions).when().post("extention/")
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorText", hasItem("Loan id cannot be empty."))
                .body("errorText", hasItem("Extention term cannot be empty."));
    }
}
