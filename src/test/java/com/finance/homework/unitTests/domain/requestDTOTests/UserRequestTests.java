package com.finance.homework.unitTests.domain.requestDTOTests;

import com.finance.homework.domain.requests.UserRequest;

import org.junit.Test;

import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserRequestTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        UserRequest userRequest = new UserRequest();
        assertThat(userRequest, notNullValue());
        assertTrue(hasFieldsWithNullValues(userRequest, Arrays.asList("firstname", "lastname", "address", "email", "phone")));
    }

    @Test
    public void shouldBeEqual() {
        UserRequest userRequest1 = presetUserRequest();
        UserRequest userRequest2 = presetUserRequest();

        assertTrue(userRequest1.equals(userRequest2));

        userRequest1 = userRequest2;
        assertEquals(userRequest1.hashCode(), userRequest2.hashCode());
        assertEquals((new UserRequest()).hashCode(), (new UserRequest()).hashCode());
    }


    private UserRequest presetUserRequest() {
        UserRequest userRequest = new UserRequest();

        userRequest.setFirstname("John");
        userRequest.setLastname("Doe");
        userRequest.setAddress("teststreet1");
        userRequest.setEmail("asdasd@adasd.com");
        userRequest.setPhone("123123123112313");

        return userRequest;
    }
}
