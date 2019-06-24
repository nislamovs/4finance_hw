package com.finance.homework.domain.responseDTOTests;

import com.finance.homework.domain.responses.UserResponse;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserResponseTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        UserResponse userResponse = new UserResponse();
        assertThat(userResponse, notNullValue());
        assertTrue(hasFieldsWithNullValues(userResponse, Arrays.asList("firstname", "lastname", "address", "email", "phone")));
    }

    @Test
    public void shouldBeEqual() {
        UserResponse userResponse1 = presetUserResponse();
        UserResponse userResponse2 = presetUserResponse();

        assertTrue(userResponse1.equals(userResponse2));

        userResponse1 = userResponse2;
        assertEquals(userResponse1.hashCode(), userResponse2.hashCode());
        assertEquals((new UserResponse()).hashCode(), (new UserResponse()).hashCode());

        userResponse1 = presetUserResponse_constructor();
        assertEquals(userResponse1.hashCode(), userResponse2.hashCode());
        assertEquals((new UserResponse()).hashCode(), (new UserResponse()).hashCode());
    }


    private UserResponse presetUserResponse() {
        UserResponse userResponse = new UserResponse();
        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        userResponse.setId(1L);
        userResponse.setFirstname("John");
        userResponse.setLastname("Doe");
        userResponse.setAddress("teststreet1");
        userResponse.setEmail("asdasd@adasd.com");
        userResponse.setPhone("123123123112313");
        userResponse.setBlocked(false);
        userResponse.setCreatedDate(dateTime);

        return userResponse;
    }

    private UserResponse presetUserResponse_constructor() {
        LocalDateTime dateTime = LocalDateTime.parse("1986-04-08 12:30",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new UserResponse(1L, "John", "Doe", "teststreet1",
                "asdasd@adasd.com", "123123123112313", false, null, dateTime);
    }
}
