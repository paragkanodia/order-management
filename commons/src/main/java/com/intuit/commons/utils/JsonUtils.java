package com.intuit.commons.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    public static JsonElement getValueFromJsonObjectForKeyIgnoringCase(JsonObject jsonObject, String key)
            throws NoSuchFieldException {

        for (String key1 : jsonObject.keySet()) {
            if (key1.equalsIgnoreCase(key)) {
                return jsonObject.get(key1);
            }
        }

        throw new NoSuchFieldException(String.format("No such key found in given Json Object, key:%s, jsonObject: %s", key, GsonUtils.convertToJsonString(jsonObject)));

    }

}
