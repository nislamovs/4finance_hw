package com.finance.homework.unitTests.services;

import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.model.UserEntity;
import com.finance.homework.repository.UserRepository;
import com.finance.homework.services.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.finance.homework.testHelperClasses.UserTestDataFactory.makeUserList;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserEntity;
import static com.finance.homework.testHelperClasses.UserTestDataFactory.newUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldRetrieveAllUsers() {
        List<UserEntity> userEntities = makeUserList();

        when(userRepository.findAll()).thenReturn(userEntities);
        List<UserEntity> actualUsers = userService.findAllUsers();

        for (int i = 0; i < userEntities.size(); i++)
            assertEquals(actualUsers.get(i), userEntities.get(i));
    }

    @Test
    public void shouldCreateNewUser() {
        UserEntity userEntity = newUserEntity();
        userEntity.setId(null);
        UserRequest userRequest = newUserRequest();

        when(userRepository.save(userEntity)).thenReturn(userEntity);
        userEntity = userService.createUser(userRequest);

        checkDataConsistency(userRequest, userEntity);
    }

    @Test
    public void shouldRetrieveUserById() {
        UserEntity userEntity = newUserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        UserEntity actualUsers = userService.getLoanInfoByUserId("1");

        assertEquals(actualUsers, userEntity);
    }

    @Test
    public void shouldBlockUsersByloanRequestCount() {
        when(userRepository.blockUsersWithTooManyLoanApplicationAttempts(3)).thenReturn(10);
        int blockedUsers = userService.blockUsersByLoanRequestCount(3);

        assertEquals(10, blockedUsers);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowExceptionOnCreatingAlreadyExistingUser() {
        UserEntity userEntity = newUserEntity();
        userEntity.setId(null);
        UserRequest userRequest = newUserRequest();
        when(userRepository.existsUserEntityByEmail(userRequest.getEmail())).thenReturn(true);
        userService.createUser(userRequest);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionOnRetrievingUserinfoById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        userService.getLoanInfoByUserId("1");
    }

    private void checkDataConsistency(UserRequest userRequest, UserEntity userEntity) {
        assertEquals(userRequest.getEmail(),      userEntity.getEmail());
        assertEquals(userRequest.getFirstname(),  userEntity.getFirstname());
        assertEquals(userRequest.getLastname(),   userEntity.getLastname());
        assertEquals(userRequest.getAddress(),    userEntity.getAddress());
        assertEquals(userRequest.getPhone(),      userEntity.getPhone());
    }
}
