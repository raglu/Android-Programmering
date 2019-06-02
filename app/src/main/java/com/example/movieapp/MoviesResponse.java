package com.example.movieapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Class representing a JSON object of a collection of movies from TMDB

public class MoviesResponse {

    @SerializedName("results")
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

}
