package com.intuit.appUtility.exception;

import com.intuit.commons.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderManagementExceptionCodes {

    public static ExceptionCode MM100 = new ExceptionCode("MM100", "Mapping exception.", false);
}
