package com.manishkprboilerplate.web_services.network;


public interface NetworkCallBack {

    void getResults(String results);
    void onError(int statusCode, String error);

}
