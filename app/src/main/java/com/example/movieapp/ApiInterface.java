package com.example.movieapp;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ApiInterface {

    @GET("/3/movie/{category}")
    Call<MovieResults> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    // https://api.themoviedb.org/3/movie/popular?api_key=9b177e3d3959df535a83552cd2527d28&language=en-US&page=1
}
