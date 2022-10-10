package com.intuit.commons.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@UtilityClass
@Slf4j
public class EnumUtils {

    /**
     * Objective of this method is to convert one enum type to another type provided
     * both the enum types have same values
     *
     * @param clz type of the required Enum
     * @param enm actual enum value
     * @return Returns the enum value of required type if found else will return null
     */
    @SuppressWarnings("rawtypes")
    public <T extends Enum<T>> T checkEnumNullAndApply(final Class<T> clz, final Enum enm) {
        if (Objects.nonNull(enm)) {
            return Enum.valueOf(clz, enm.toString());
        }
        return null;
    }

    public <E extends Enum<E>> E lookup(Class<E> e, String id) {
        E result = null;
        try {
            result = Enum.valueOf(e, id);
        } catch (IllegalArgumentException iae) {
            log.warn("Invalid value for enum " + iae.getMessage() + ": " + id, iae);
        } catch (Exception ex) {
            log.error("Invalid value for enum " + ex.getMessage() + ": " + id, ex);
        }
        return result;
    }

    public <T extends Enum<T>> boolean isEnumPresentInAnotherEnum(Class<T> clz, Enum enm) {
        try {
            Enum.valueOf(clz, enm.toString());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean compareEnumValue(Enum e, String value) {
        if (Objects.nonNull(e)) {
            return e.name().equals(value);
        }
        return false;
    }
}
