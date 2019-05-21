package com.finance.homework.domain.exceptions;

public class InvalidStatusException extends RuntimeException {

    public InvalidStatusException(String msg) {
        super(msg);
    }

    public InvalidStatusException(String msg, Throwable t) {
        super(msg, t);
    }
}
