package com.manishkprboilerplate.web_services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Munish Kapoor on 15/2/17.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .build();

        long t1 = System.nanoTime();
        Timber.i(String.format("Sending request %s on %s%n%s", original.url(), chain.connection(), request.headers().toString()));

        long t2 = System.nanoTime();
        Response response = chain.proceed(request);

        Timber.i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;

    }

}
