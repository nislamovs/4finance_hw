package com.finance.homework.controllers.documentation;

import com.finance.homework.domain.exceptions.UserNotFoundException;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.domain.responses.ErrorResponse;
import com.finance.homework.domain.responses.UserResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@Api(tags = { "User management" },  description = "Api calls for user operations")
public interface UserControllerDoc {

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve all users with their loans and extention",
            value = "Get all users.",
            response = UserResponse.class,
            responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users with their data are retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    @Transactional
    ResponseEntity<?> getAllUserList();


    @ApiOperation(
            httpMethod = "POST",
            notes = "Resource to create new user",
            value = "Create new user.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    @Transactional
    ResponseEntity<?> createUser(@ApiParam(value = "UserRequest object", required = true, name = "User request") UserRequest userRequest);


    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve user's loans and extention by user id",
            value = "Get user data by id.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Particular user retrieved with loans and extentions.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class)
    })
    @Transactional
    ResponseEntity<?> getUserLoans(@ApiParam(value = "ID", required = true, name = "User id", example = "5", defaultValue = "3") String userId) throws UserNotFoundException;
}
