package com.manishkprboilerplate.web_services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Munish Kapoor on 15/2/17.
 */

public class ClientOkHttp {

    static final int TIME_OUT   =         120;

    public static OkHttpClient getOKHTTPClient(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(TIME_OUT, TimeUnit.SECONDS).connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new LoggingInterceptor());

        OkHttpClient client = httpClient.build();


        return client;

    }

}
