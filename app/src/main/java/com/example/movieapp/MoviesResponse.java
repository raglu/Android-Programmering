package com.example.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    @Expose
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

}
