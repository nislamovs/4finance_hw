package com.finance.homework.controllers.documentation;

import com.finance.homework.domain.exceptions.ExtentionNotFoundException;
import com.finance.homework.domain.exceptions.LoanNotFoundException;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ErrorResponse;
import com.finance.homework.domain.responses.ExtentionResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Api(tags = { "Extention management" },  description = "Api calls for operations on extentions")
public interface ExtentionControllerDoc {

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to get all extentions",
            value = "Get extentions.",
            response = ExtentionResponse.class,
            responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extentions  were retrieved.", response = ExtentionResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    ResponseEntity<?> getAllExtentions();

    @ApiOperation(
            httpMethod = "POST",
            notes = "Resource to create new extention",
            value = "Create new extention.",
            response = ExtentionResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Extention created.", response = ExtentionResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    ResponseEntity<?> createExtention(@ApiParam(value = "ExtentionRequest object", required = true, name = "Extention request")
                                              ExtentionRequest extentionRequest) throws LoanNotFoundException;

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to get extention by id",
            value = "Get extention by id.",
            response = ExtentionResponse.class,
            responseContainer = "List",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extention retrieved.", response = ExtentionResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Extention not found.", response = ErrorResponse.class)
    })
    ResponseEntity<?> getExtention(@ApiParam(value = "Extention id", required = true, name = "Extention id") String extId)
            throws ExtentionNotFoundException;
}
