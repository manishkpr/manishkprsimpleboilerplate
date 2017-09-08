package com.manishkprboilerplate.web_services.network;



import com.manishkprboilerplate.models.HttpResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


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

    public static HttpResponse postMethod(String urlAddress,HashMap<String, String> postDataParams){


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
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true); //whether this URLConnection allows receiving data
            conn.setDoOutput(true);
            conn.connect();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

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

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
