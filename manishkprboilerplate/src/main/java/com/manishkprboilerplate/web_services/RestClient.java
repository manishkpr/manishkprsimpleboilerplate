package com.manishkprboilerplate.web_services;

import android.app.Activity;


import com.manishkprboilerplate.web_services.network.NetworkCallBack;
import com.manishkprboilerplate.web_services.network.RequestTask;

import java.util.HashMap;

public class RestClient {

    private String url = "http://api.openweathermap.org/data/2.5/weather";

    static RestClient restClient;

    public static synchronized RestClient getInstance(Activity activity){

        if(restClient==null){
            restClient = new RestClient(activity);
        }

        return restClient;

    }

    Activity activity;
    RequestTask requestTask;

    public RequestTask getRequestTask() {
        return requestTask;
    }

    public void setRequestTask(RequestTask requestTask) {
        this.requestTask = requestTask;
    }

    public RestClient(Activity activity){
        this.activity = activity;
    }

    public RestClient url(String url) {
        this.url = url;
        return this;
    }

    public RestClient addGetParam(HashMap<String,String> param) {

        for (HashMap.Entry<String,String> entry : param.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (!url.contains("?")) {
                url = new StringBuilder(url).append("?").append(key).append("=").append(value).toString();
            } else {
                url = new StringBuilder(url).append("&").append(key).append("=").append(value).toString();
            }

        }
        return this;
    }

    public void getRequest(NetworkCallBack callBack){
        setRequestTask(new RequestTask(activity).url(url).callBack(callBack));
        getRequestTask().execute();
    }

    public void cancelRequest(){
        if(requestTask!=null){
            requestTask.cancel(true);
        }
    }

}
