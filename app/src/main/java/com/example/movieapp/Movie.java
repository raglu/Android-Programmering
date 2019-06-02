package com.example.movieapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Class representing a single movie JSON object from TMDB

public class Movie {

    private int id;

    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    private String overview;

    @SerializedName("backdrop_path")
    private String backdrop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

}