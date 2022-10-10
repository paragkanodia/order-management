package com.intuit.commons.rest;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RetrofitConfig {

    private HeaderProvider headerProvider;

    private RestClientProperties properties;

    public Retrofit getRetrofit() {
        return getRetrofit(null, null, GsonConverterFactory.create());
    }

    public Retrofit getRetrofit(Converter.Factory factory) {
        return getRetrofit(null, null, factory);
    }

    public RetrofitConfig(RestClientProperties properties, HeaderProvider headerProvider) {
        this.properties = properties;
        this.headerProvider=headerProvider;
    }

    public Retrofit getRetrofit(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
        return getRetrofit(sslSocketFactory, trustManager, GsonConverterFactory.create());
    }

    public Retrofit getRetrofit(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager, Converter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(properties.getBaseUri())
                .addConverterFactory(factory)
                .client(getOkHttpClientBuilder(sslSocketFactory, trustManager))
                .build();
    }

    private OkHttpClient getOkHttpClientBuilder(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
        okhttp3.OkHttpClient.Builder clientBuilder = (new okhttp3.OkHttpClient.Builder())
                .connectTimeout((long)this.properties.connectTimeoutSecs(), TimeUnit.SECONDS)
                .readTimeout((long)this.properties.readTimeoutSecs(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(this.properties.poolSize(), (long)this.properties.keepAliveDurationInSecs(), TimeUnit.SECONDS))
                .writeTimeout((long)this.properties.writeTimeoutSecs(), TimeUnit.SECONDS);

        if (Objects.nonNull(this.headerProvider)) {
            clientBuilder.addInterceptor(new HeaderInjector(this.headerProvider));
        }

        if (Objects.nonNull(sslSocketFactory) && Objects.nonNull(trustManager)) {
            clientBuilder.sslSocketFactory(sslSocketFactory, trustManager);
        }

        return clientBuilder.build();
    }
}
