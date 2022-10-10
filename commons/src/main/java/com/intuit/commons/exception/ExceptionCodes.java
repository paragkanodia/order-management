package com.intuit.commons.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionCodes {

    //Access related exception codes
    public ExceptionCode A101 = new ExceptionCode("A101", "Access Denied.", false);

    //Validation related exception codes
    public ExceptionCode V101 = new ExceptionCode("V101", "Validation error occurred.", false);
    public ExceptionCode V102 = new ExceptionCode("V102", "Resource not found.", false);

    //Remote Service related exception codes
    public ExceptionCode R101 = new ExceptionCode("R101", "Exception occurred at remote service.", false);

    //Unknown error related exception codes
    public ExceptionCode U100 = new ExceptionCode("U100", "Unknown exception occurred.", false);
    public ExceptionCode U101 = new ExceptionCode("U101", "Unable to parse payload.", false);
}
