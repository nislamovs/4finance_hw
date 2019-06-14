package com.finance.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.homework.controllers.ExceptionHandlingController;
import com.finance.homework.controllers.UserController;
import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.services.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserRequest;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlingControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    UserController userController = new UserController();

    @InjectMocks
    ExceptionHandlingController exceptionHandlingController = new ExceptionHandlingController();

    @Mock
    UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(exceptionHandlingController)
                .build();
    }

    @Test
    public void shouldThrowUserNotFoundExceptionAndReturn400ResponseCode() throws Exception {
        when(userService.getLoanInfoByUserId("1")).thenThrow(new UserNotFoundException("User not found."));

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("User not found.")));
    }

    @Test
    public void shouldThrowUserAlreadyExistsExceptionAndReturn404ResponseCode() throws Exception {
        UserRequest userRequest = newUserRequest();
        when(userService.createUser(userRequest)).thenThrow(new UserAlreadyExistsException("User already exists."));

        mockMvc.perform(post("/api/v1/user/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("User already exists.")));
    }

    @Test
    public void shouldThrowGenericExceptionAndReturn500ResponseCode() throws Exception {
        when(userService.getLoanInfoByUserId("1")).thenThrow(new NullPointerException("Magic error"));

        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().isInternalServerError())
//                .andDo(result -> {System.out.println(">>>   "+result.getResponse().getContentAsString());})
                .andExpect(content().string(containsString("Unexpected problem encountered. Please contact support team")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
