package com.manishkprboilerplate.web_services.network;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.manishkprboilerplate.R;
import com.manishkprboilerplate.models.HttpResponse;

import java.util.HashMap;


public class RequestTask extends AsyncTask<HttpResponse,Void,HttpResponse> {

    final static String TAG    =   WebHttp.class.getClass().getSimpleName();

    Activity activity;
    String url;
    NetworkCallBack callBack;
    HashMap<String,String> param;

    public RequestTask (Activity activity){
        this.activity = activity;
    }

    public RequestTask url(String url) {
        this.url = url;
        return this;
    }
    public RequestTask param( HashMap<String,String> param) {
        this.param = param;
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
        if(this.param==null) {
            result = WebHttp.getMethod(url);
            Log.e(getClass().getSimpleName(),"Executing get...");
        }else{
            result = WebHttp.postMethod(url,param);
            Log.e(getClass().getSimpleName(),"Executing post...");
        }
        return result;
    }

    @Override
    protected void onPostExecute(HttpResponse s) {
        super.onPostExecute(s);
        if(s.getStatusCode()==200) {
            callBack.getResults(s.getResponse());
            Log.e(getClass().getSimpleName(),"Response"+s.getResponse().toString());
        }else{
            Log.e(getClass().getSimpleName(),"Response"+s.getResponse().toString());
            callBack.onError(s.getStatusCode(),s.getResponse());
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.e(TAG,"cancelled");
    }
}
