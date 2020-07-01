package com.themovie.service;

import com.themovie.models.ResponseUpComing;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MovieUpcomingService {
    @GET("/3/movie/upcoming")
    void getMovies(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("page") int page,
            Callback<ResponseUpComing> callback
    );
}
