package com.finance.homework.services;

import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAllUsers();

    UserEntity createUser(UserRequest user) throws UserAlreadyExistsException;

    UserEntity getLoanInfoByUserId(String userId) throws UserNotFoundException;

    int blockUsersByLoanRequestCount(int attempts);
}
