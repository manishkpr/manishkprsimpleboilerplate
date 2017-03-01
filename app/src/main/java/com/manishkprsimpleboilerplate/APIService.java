package com.manishkprsimpleboilerplate;

import com.manishkprsimpleboilerplate.models.Suggestions;


import java.util.Map;

import retrofit2.http.GET;

import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Munish on 15/2/17.
 */

public interface APIService {

    @GET("suggestion.php")
    Observable<Suggestions> getSuggestions(@QueryMap(encoded = true) Map<String, String> params);

}
