package com.intuit.commons.rest;

import com.intuit.commons.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class HeaderInjector implements Interceptor {

    private HeaderProvider headerProvider;

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(
                chain.request().newBuilder().headers(mergeHeaders(chain.request().headers(), headerProvider.getHeaders())).build()
        );
    }

    private Headers mergeHeaders(Headers existingHeaders, Map<String, String> providedHeaders) {
        Map<String, String> headers = new HashMap<>();
        if (Objects.nonNull(existingHeaders) && existingHeaders.size() > 0) {
            existingHeaders.names().forEach(n -> headers.put(n, existingHeaders.get(n)));
        }
        if (!CollectionUtils.isEmpty(providedHeaders)) {
            headers.putAll(providedHeaders);
        }
        return Headers.of(headers);
    }
}
