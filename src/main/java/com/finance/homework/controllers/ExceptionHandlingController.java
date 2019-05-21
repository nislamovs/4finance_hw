package com.finance.homework.controllers;

import com.finance.homework.domain.exceptions.*;
import com.finance.homework.domain.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestControllerAdvice
@PropertySource(value={"classpath:application.yml"})
public class ExceptionHandlingController {

    @Autowired
    Environment environment;

    @Value("${application.support.email}")
    private String supportEmail;


    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ValidationException.class,
            InvalidStatusException.class,
            UserAlreadyExistsException.class,
            IllegalArgumentException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class})
    public ErrorResponse handleValidationException(final Exception ex) {
        log.info("Bad request : Validation error " + ex.getMessage());

        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class,
            LoanNotFoundException.class,
            ExtentionNotFoundException.class})
    public ErrorResponse handleNotFoundException(final Exception ex) {
        log.info("Bad request : Requested data not found " + ex.getMessage());

        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericException(final Exception ex) {
        log.error("Internal exception: ", ex);

        final String message = "Unexpected problem encountered. Please contact support team (" + supportEmail + ") with timestamp ["
                + DATETIME_FORMAT.format(new Date()) + "] and short description.";

        return new ErrorResponse(message);
    }
}
