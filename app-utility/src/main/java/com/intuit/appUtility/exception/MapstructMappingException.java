package com.intuit.appUtility.exception;

import com.intuit.commons.exception.ValidationException;

public class MapstructMappingException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public MapstructMappingException(String errorMessage) {
        super(SapphireExceptionCodes.MM100, errorMessage);
    }
}
