package com.intuit.commons.exception;

public class ValidationException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }

    public ValidationException(ExceptionCode exceptionCode, String errorMessage) {
        super(exceptionCode, errorMessage);
    }
}
