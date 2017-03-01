package com.manishkprboilerplate.web_services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Munish Kapoor on 15/2/17.
 */

public class ClientOkHttp {

    public static OkHttpClient getOKHTTPClient(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new LoggingInterceptor());

        OkHttpClient client = httpClient.build();

        return client;

    }

}
