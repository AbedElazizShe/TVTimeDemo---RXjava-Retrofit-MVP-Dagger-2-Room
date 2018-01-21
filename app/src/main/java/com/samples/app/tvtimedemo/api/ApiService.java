package com.samples.app.tvtimedemo.api;

import com.samples.app.tvtimedemo.mvp.model.TVShowsResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.samples.app.tvtimedemo.util.Constants.DISCOVER;

/**
 * Created by Abed Elaziz Shehadeh on 1/20/2018.
 * elaziz.shehadeh@gmail.com
 */

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET(DISCOVER + "{type}")
    Observable<TVShowsResponse> fetchTVShows(
            @Path("type") String type,
            @QueryMap Map<String, String> popularShowsRequestQueries);

}
