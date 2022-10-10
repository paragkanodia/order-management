package com.intuit.commons.rest;

import java.util.HashMap;
import java.util.Map;

public interface HeaderProvider {

    default Map<String, String> getHeaders() {
        return new HashMap<>();
    }
}
