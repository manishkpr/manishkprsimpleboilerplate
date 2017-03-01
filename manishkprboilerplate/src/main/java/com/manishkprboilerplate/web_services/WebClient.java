package com.manishkprboilerplate.web_services;

/**
 * Created by edge on 15/2/17.
 */

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {


    private  Retrofit retrofit = null;

    public static WebClient webClient;

    public String BASE_URL                          = "http://webheavens.com/";

    public static synchronized WebClient getInstance(){

        if(webClient==null) {
            webClient = new WebClient();
        }
        return webClient;
    }

    public  Retrofit getClient(String baseUrl) {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl((baseUrl==null) ? BASE_URL : baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ClientOkHttp.getOKHTTPClient())
                    .build();
        }

        return retrofit;

    }

    public WebClient URL(String url){
        BASE_URL = url;
        return this;
    }


}
