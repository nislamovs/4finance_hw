package com.finance.homework.domain.exceptions;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(String msg) {
        super(msg);
    }

    public LoanNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
