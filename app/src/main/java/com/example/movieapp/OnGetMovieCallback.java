package com.example.movieapp;

import java.util.List;

public interface OnGetMovieCallback {

    void onSuccess(Movie movie);

    void onError();
}


