package com.intuit.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class ProviderFactory<T, V> {

    private final Map<T, V> registry = new HashMap<>();

    private boolean isDuplicateAllowed;

    public ProviderFactory() {
        this.isDuplicateAllowed = false;
    }

    public ProviderFactory(boolean isDuplicateAllowed) {
        this.isDuplicateAllowed = isDuplicateAllowed;
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public void registerService(T key, V value) {
        notNull(key, "Key cannot be null");
        notNull(value, "Value cannot be null");
        if (!isDuplicateAllowed && registry.containsKey(key)) {
            throw new IllegalArgumentException("Already service exist with key " + key.toString());
        }
        registry.put(key, value);
    }

    public V getService(T key) {
        notNull(key, "Key cannot be null");
        if (registry.containsKey(key)) {
            return registry.get(key);
        }
        throw new IllegalArgumentException("No service found for key " + key.toString());
    }
}
