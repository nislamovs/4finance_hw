package com.finance.homework.controllers;

import com.finance.homework.controllers.documentation.UserControllerDoc;
import com.finance.homework.converters.UserConverter;
import com.finance.homework.domain.exceptions.UserAlreadyExistsException;
import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.domain.responses.UserResponse;
import com.finance.homework.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController implements UserControllerDoc {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUserList() {

        log.info("Retrieving user list with all loans and extentions.");

        return new ResponseEntity<List<UserResponse>>(UserConverter.toResponse(userService.findAllUsers()), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) throws UserAlreadyExistsException {

        log.info("Registering new user: {}", userRequest);

        UserResponse ur = UserConverter.toResponse(userService.createUser(userRequest));

        return new ResponseEntity<UserResponse>(ur , HttpStatus.CREATED);
    }


    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUserLoans(@PathVariable final String userId) throws UserNotFoundException {

        log.info("Retrieving loans with extentions by user id: {}", userId);

        return new ResponseEntity<UserResponse>(UserConverter.toResponse(userService.getLoanInfoByUserId(userId)), HttpStatus.OK);
    }
}
