package com.manishkprboilerplate.web_services.network;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.manishkprboilerplate.R;
import com.manishkprboilerplate.models.HttpResponse;


public class RequestTask extends AsyncTask<HttpResponse,Void,HttpResponse> {

    final static String TAG    =   WebHttp.class.getClass().getSimpleName();

    Activity activity;
    String url;
    NetworkCallBack callBack;

    public RequestTask (Activity activity){
        this.activity = activity;
    }

    public RequestTask url(String url) {
        this.url = url;
        return this;
    }

    public RequestTask callBack(NetworkCallBack callBack) {
        this.callBack = callBack;
        return this;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!new ConnectionDetector(activity).isConnectingToInternet()){
            this.cancel(true);
            callBack.onError(-1,activity.getString(R.string.err_no_internet));
        }
    }

    @Override
    protected HttpResponse doInBackground(HttpResponse... params) {
        HttpResponse result = null;
        result =  WebHttp.getMethod(url);
        return result;
    }

    @Override
    protected void onPostExecute(HttpResponse s) {
        super.onPostExecute(s);
        if(s.getStatusCode()==200) {
            callBack.getResults(s.getResponse());
        }else{
            callBack.onError(s.getStatusCode(),s.getResponse());
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.e(TAG,"cancelled");
    }
}
