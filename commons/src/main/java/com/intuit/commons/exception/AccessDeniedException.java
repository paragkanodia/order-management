package com.intuit.commons.exception;

public class AccessDeniedException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public AccessDeniedException(ExceptionCode exceptionCode, String errorMessage) {
        super(exceptionCode, errorMessage);
    }
}
