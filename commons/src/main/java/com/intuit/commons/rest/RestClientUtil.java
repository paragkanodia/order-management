package com.intuit.commons.rest;

import com.intuit.commons.dto.ApiResponse;
import retrofit2.Call;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class RestClientUtil {

    private ClientContext clientContext;

    public RestClientUtil(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public <T> CompletableFuture<T> toCompletableFuture(Call<T> call) {
        return HttpClientUtil.toCompletableFuture(call, getApplicationId());
    }

    public <T> CompletableFuture<ApiResponse<T>> toCompletableFutureOfApiResponse(Call<T> call) {
        return HttpClientUtil.toCompletableFutureOfApiResponse(call, getApplicationId());
    }

    private String getApplicationId() {
        if (Objects.nonNull(clientContext)) {
            return clientContext.getApplication();
        }
        return null;
    }
}
