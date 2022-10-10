package com.intuit.appUtility.exception;

import com.intuit.commons.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SapphireExceptionCodes {

    public static ExceptionCode MM100 = new ExceptionCode("MM100", "Mapping exception.", false);


    public static ExceptionCode RG100 = new ExceptionCode("RG100", "Ganymede client creation exception.", true);

    //Resource Not found exceptions
    public static ExceptionCode RNGT100 = new ExceptionCode("RNGT100", "Ganymede config hot found.", false);
    public static ExceptionCode RNFPS100 = new ExceptionCode("RNFPS100", "Policy specification not available.", true);
    public static ExceptionCode RNFA100 = new ExceptionCode("RNFA100", "Account does not exists.", false);
    public static ExceptionCode PCE100 = new ExceptionCode("PCE100", "Exception while handling post classification event", false);
    public static ExceptionCode PCE101 = new ExceptionCode("PCE101", "Attribute values not composable while handling post classification event", false);

    @NoArgsConstructor
    public static final class PolicyErrorCodes {
        public static ExceptionCode POLICY_EXISTS_ON_DIFF_PRODUCT = new ExceptionCode("POLICY_EXISTS_ON_DIFF_PRODUCT", "Policy exists, but for some other product", false);
        public static ExceptionCode POLICY_EXISTS_ON_SAME_PRODUCT = new ExceptionCode("POLICY_EXISTS_ON_SAME_PRODUCT", "Policy exists, for this product itself", false);
        public static ExceptionCode POLICY_EXISTS_ON_SAME_TENANT = new ExceptionCode("POLICY_EXISTS_ON_SAME_TENANT", "Policy exists, for this tenant itself", false);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ProductPreference {
        public static final ExceptionCode NUMBER_OF_ACCOUNTS_INVALID = new ExceptionCode("NUMBER_OF_ACCOUNTS_INVALID", "Number of accounts is invalid", false);
        public static final ExceptionCode START_DATE_INVALID = new ExceptionCode("START_DATE_INVALID", "Start date in invalid", false);
        public static final ExceptionCode EXPIRY_DATE_INVALID = new ExceptionCode("EXPIRY_DATE_INVALID", "Expiry date is invalid", false);
        public static final ExceptionCode START_DATE_UPDATE_INVALID = new ExceptionCode("START_DATE_UPDATE_INVALID", "Start date is invalid for update", false);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Account {
        public static final ExceptionCode PRODUCT_EXPIRED = new ExceptionCode("PRODUCT_EXPIRED", "Product is expired", false);
        public static final ExceptionCode PRODUCT_NOT_AVAILABLE_YET = new ExceptionCode("PRODUCT_NOT_AVAILABLE_YET", "Product is not available yet", false);
        public static final ExceptionCode MAXIMUM_ACCOUNTS_PER_ACCOUNT_HOLDER_REACHED = new ExceptionCode("MAXIMUM_ACCOUNTS_PER_ACCOUNT_HOLDER_REACHED", "Maximum accounts per account holder reached", false);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class FormFactorSpendLimitPolicy {
        public static final ExceptionCode CONFIG_DUPLICATE = new ExceptionCode("CONFIG_DUPLICATE", "Form factor configurations has to be unique per product", false);
        public static final ExceptionCode LOCALE_DUPLICATE = new ExceptionCode("LOCALE_DUPLICATE", "Locales under form factor configuration has to be unique", false);
        public static final ExceptionCode CHANNEL_DUPLICATE = new ExceptionCode("CHANNEL_DUPLICATE", "Channels under form factor limit configuration has to be unique", false);
        public static final ExceptionCode PERIODICITY_DUPLICATE = new ExceptionCode("PERIODICITY_DUPLICATE", "Periodicity under form factor limit configuration has to be unique", false);
        public static final ExceptionCode CYCLE_DUPLICATE = new ExceptionCode("CYCLE_DUPLICATE", "Cycle URI under limit configurations has to be unique", false);
        public static final ExceptionCode LIMIT_CONFIG_AVAILABILITY = new ExceptionCode("LIMIT_CONFIG_AVAILABILITY", "There has to be at least one limit configuration available when policy is enabled", false);
        public static final ExceptionCode INVALID_LIMIT = new ExceptionCode("INVALID_LIMIT", "Limit configuration is invalid because of incorrect combination", false);
        public static final ExceptionCode POS_CONTACTLESS_SUBSET = new ExceptionCode("POS_CONTACTLESS_SUBSET", "POS_CONTACTLESS spend limit should be less than equal to POS spend limit", false);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class FormFactor {
        public static final ExceptionCode FORM_FACTOR_SPEND_POLICY_NOT_ENABLED = new ExceptionCode("FORM_FACTOR_SPEND_POLICY_NOT_ENABLED", "Form Factor spend policy is not enabled", false);
        public static final ExceptionCode FORM_FACTOR_SPEND_POLICY_NOT_FOUND = new ExceptionCode("FORM_FACTOR_SPEND_POLICY_NOT_FOUND", "Form Factor spend policy not found", false);
        public static final ExceptionCode DUPLICATE_FORM_FACTOR_SPEND_POLICY_CONFIGS = new ExceptionCode("DUPLICATE_FORM_FACTOR_SPEND_POLICY_CONFIGS", "Duplicate form factor spend policy configs found", false);
        public static final ExceptionCode FORM_FACTOR_HANDLING_NOT_SUPPORTED = new ExceptionCode("FORM_FACTOR_HANDLING_NOT_SUPPORTED", "Form Factor Handling not supported", false);
        public static final ExceptionCode FORM_FACTOR_NOT_FOUND = new ExceptionCode("FORM_FACTOR_NOT_FOUND", "Form Factor not found", false);
        public static final ExceptionCode FORM_FACTOR_CREATION_NOT_COMPLETED = new ExceptionCode("FORM_FACTOR_CREATION_NOT_COMPLETED", "Form Factor creation is not completed", false);
        public static final ExceptionCode DEFAULT_POLICY_LIMIT_NOT_FOUND = new ExceptionCode("DEFAULT_POLICY_LIMIT_NOT_FOUND", "Default policy limit not found", false);
        public static final ExceptionCode CANNOT_EXCEED_POLICY_LIMIT = new ExceptionCode("CANNOT_EXCEED_POLICY_LIMIT", "Cannot exceed policy limit", false);
        public static final ExceptionCode SPEND_CHANNEL_DISABLED_IN_POLICY = new ExceptionCode("SPEND_CHANNEL_DISABLED_IN_POLICY", "Spend channel disabled in policy", false);
    }
}
