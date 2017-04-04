package com.manishkprboilerplate.web_services.network;



import com.manishkprboilerplate.models.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WebHttp {

    final static String TAG    =   WebHttp.class.getClass().getSimpleName();

    private static final int CONNECTION_TIMEOUT = 1000;
    private static final int READ_INTERVAL_TIMEOUT = 10000 ;

    public static HttpResponse getMethod(String urlAddress){


        URL url;
        HttpURLConnection conn=null;
        InputStream inputStream = null;
        InputStreamReader reader = null;
        StringBuilder response = new StringBuilder("");
        int statusCode = 0;
        try {
            url = new URL(urlAddress);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_INTERVAL_TIMEOUT); //maximum time to wait for an input stream read
            conn.setConnectTimeout(CONNECTION_TIMEOUT); //maximum time to wait while connecting
            conn.setRequestMethod("GET");
            conn.setDoInput(true); //whether this URLConnection allows receiving data
            conn.connect();
            int status = conn.getResponseCode();
            statusCode = status;
            if(status == 200) {
                inputStream = conn.getInputStream();
            }
            else {
                inputStream = conn.getErrorStream();
            }

            reader = new InputStreamReader(inputStream);


            int data = reader.read();
            while(data!=-1){
                response.append( (char)data );
                data = reader.read();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{

            try {
                if(reader!=null) reader.close();
                if(inputStream!=null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(conn!=null) conn.disconnect();

        }

        return new HttpResponse(statusCode,response.toString());

    }

}
