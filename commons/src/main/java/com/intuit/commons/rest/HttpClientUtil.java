package com.intuit.commons.rest;

import com.intuit.commons.dto.ApiResponse;
import com.intuit.commons.exception.ExceptionCodes;
import com.intuit.commons.exception.RemoteServiceException;
import lombok.experimental.UtilityClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@UtilityClass
public class HttpClientUtil {

    public static <T> CompletableFuture<T> toCompletableFuture(Call<T> call) {
        CompletableFuture<T> cf = new CompletableFuture<>();
        call.enqueue(convertToCallback(cf, null));
        return cf;
    }

    public static <T> CompletableFuture<T> toCompletableFuture(Call<T> call, String appId) {
        CompletableFuture<T> cf = new CompletableFuture<>();
        call.enqueue(convertToCallback(cf, appId));
        return cf;
    }

    private static <T> Callback<T> convertToCallback(final CompletableFuture<T> cf, final String appId) {
        return new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    cf.complete(response.body());
                } else {
                    try {
                        cf.completeExceptionally(new RemoteServiceException(appId ,response.code(), ExceptionCodes.R101,
                                Objects.nonNull(response.errorBody()) ? response.errorBody().string() : "Unknown error occurred"));
                    } catch (IOException var4) {
                        cf.completeExceptionally(new Throwable("Request processing failed"));
                    }
                }
            }

            public void onFailure(Call<T> call, Throwable throwable) {
                cf.completeExceptionally(throwable);
            }
        };
    }

    public static <T> CompletableFuture<ApiResponse<T>> toCompletableFutureOfApiResponse(Call<T> call, String appId) {
        CompletableFuture<ApiResponse<T>> cf = new CompletableFuture<>();
        call.enqueue(toCallback(cf, appId));
        return cf;
    }

    private static <T> Callback<T> toCallback(final CompletableFuture<ApiResponse<T>> cf, final String appId) {
        return new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    cf.complete(new ApiResponse<>(response.headers().toMultimap(), response.body()));
                } else {
                    try {
                        cf.completeExceptionally(new RemoteServiceException(appId, response.code(),ExceptionCodes.R101, Objects.nonNull(response.errorBody()) ? response.errorBody().string() : "Unknown error occurred"));
                    } catch (IOException var4) {
                        cf.completeExceptionally(new Throwable("Request processing failed"));
                    }
                }
            }

            public void onFailure(Call<T> call, Throwable throwable) {
                cf.completeExceptionally(throwable);
            }
        };
    }
}
