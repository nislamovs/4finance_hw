package com.finance.homework.unitTests.controllers;

import com.finance.homework.controllers.UserController;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.domain.responses.UserResponse;
import com.finance.homework.model.UserEntity;
import com.finance.homework.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.finance.homework.testHelperClasses.UserTestDataFactory.makeUserList;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserEntity;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @InjectMocks
    UserController userController = new UserController();

    @Mock
    UserService userService;

    @Test
    public void shouldRetrieveAllUsers() {
        List<UserEntity> userList = makeUserList();
        when(userService.findAllUsers()).thenReturn(userList);

        ResponseEntity<?> response = userController.getAllUserList();
        List<UserResponse> userResponse = (List<UserResponse>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList.get(0).getFirstname(), userResponse.get(0).getFirstname());
        assertEquals(userList.get(2).getAddress(), userResponse.get(2).getAddress());
    }


    @Test
    public void shouldCreateNewUser() {
        UserRequest userRequest = newUserRequest();
        UserEntity userEntity = newUserEntity();
        when(userService.createUser(userRequest)).thenReturn(userEntity);

        ResponseEntity<?> response = userController.createUser(userRequest);
        UserResponse userResponse = (UserResponse) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userRequest.getFirstname(), userResponse.getFirstname());
        assertEquals(userRequest.getLastname(), userResponse.getLastname());
    }

    @Test
    public void shouldRetrieveUserById() {
        UserEntity userEntity = newUserEntity();
        when(userService.getLoanInfoByUserId("1")).thenReturn(userEntity);

        ResponseEntity<?> response = userController.getUserLoans("1");
        UserResponse userResponse = (UserResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userEntity.getId(), userResponse.getId());
        assertEquals(userEntity.getEmail(), userResponse.getEmail());
        assertEquals(userEntity.getAddress(), userResponse.getAddress());
    }
}
