package com.intuit.commons.rest;

public interface RestClientProperties {

    String getBaseUri();

    default Integer connectTimeoutSecs() {
        return 30;
    }

    default Integer readTimeoutSecs() {
        return 15;
    }

    default Integer writeTimeoutSecs() {
        return 30;
    }

    default Integer poolSize() {
        return 25;
    }

    default Integer keepAliveDurationInSecs() {
        return 60;
    }
}
