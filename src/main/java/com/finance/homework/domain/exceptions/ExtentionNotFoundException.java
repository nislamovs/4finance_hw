package com.finance.homework.domain.exceptions;

public class ExtentionNotFoundException extends RuntimeException {

    public ExtentionNotFoundException(String msg) {
        super(msg);
    }

    public ExtentionNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
