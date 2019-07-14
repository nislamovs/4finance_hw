package com.finance.homework.integrationTests;

import com.finance.homework.domain.requests.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("integration-test")
public class UsercontrollerTests extends AbstractIntegrationTest {

    @Test
    @DisplayName("Retrieving all users with loans and extentions")
    public void shouldRetrieveAllUsers() {
        given().get("user/")
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", hasItems("Aleksandrs", "Peteris", "Vladislavs", "Kenny", "Fred"))
                .body("lastname", hasItems("Collins", "Klava", "Strass", "Jones", "Mazur"));

    }

    @Test
    @DisplayName("Creating new user")
    public void shouldCreateNewUser() {
        given().body(newUserRequest()).when().post("user/")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("firstname", equalTo("JohnTest"));
    }

    @Test
    @DisplayName("Retrieving user data by id")
    public void shouldGetUserById() {
        given().get("user/{id}", 5)
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", equalTo("Fred"))
                .body("email", equalTo("Dysfunctional@gmail.com"));
    }

    @Test
    @DisplayName("Retrieving user data by wrong id")
    public void shouldFailOnGetUserByWrongId() {
        given().get("user/{id}", 100500)
                .then().statusCode(HttpStatus.NOT_FOUND.value())
                .body("errorText", containsString("Username with id [100500] not found."));
    }

    @Test
    @DisplayName("Creating new user with empty fields")
    public void shouldFailOnCreateNewUser_EmptyFields() {
        UserRequest user = new UserRequest();

        given().body(user).when().post("user/")
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorText", hasItem("Lastname cannot be empty."))
                .body("errorText", hasItem("Address cannot be empty."))
                .body("errorText", hasItem("Email cannot be empty."))
                .body("errorText", hasItem("Firstname cannot be empty."))
                .body("errorText", hasItem("Phone cannot be empty."));
    }
}
