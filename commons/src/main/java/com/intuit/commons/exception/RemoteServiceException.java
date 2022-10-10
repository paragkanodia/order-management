package com.intuit.commons.exception;

import lombok.Getter;

public class RemoteServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;
    @Getter
    private String application;

    @Getter
    private int httpCode;

    public RemoteServiceException(String application, int httpCode, ExceptionCode exceptionCode, String errorMessage) {
        super(exceptionCode, errorMessage);
        this.application = application;
        this.httpCode = httpCode;
    }

    public RemoteServiceException(String application, ExceptionCode exceptionCode, String errorMessage) {
        super(exceptionCode, errorMessage);
        this.application = application;
    }
}
