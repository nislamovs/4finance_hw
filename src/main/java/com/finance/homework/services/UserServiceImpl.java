package com.finance.homework.services;

import com.finance.homework.converters.UserConverter;
import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.model.UserEntity;
import com.finance.homework.repository.LoanRepository;
import com.finance.homework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserRequest userRequest) throws UserAlreadyExistsException {
        if (userRepository.existsUserEntityByEmail(userRequest.getEmail()))
            throw new UserAlreadyExistsException("User with email {" + userRequest.getEmail() + "} already exists.");

        return userRepository.save(UserConverter.toEntity(userRequest));
    }

    @Override
    public UserEntity getLoanInfoByUserId(String userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(Long.parseLong(userId));
        user.orElseThrow(() -> new UserNotFoundException("Username with id [" + userId + "] not found."));

        return user.get();
    }

    @Override
    public int blockUsersByLoanRequestCount(int attempts) {
        return userRepository.blockUsersWithTooManyLoanApplicationAttempts(attempts);
    }
}
