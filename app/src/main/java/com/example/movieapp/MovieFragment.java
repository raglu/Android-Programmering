/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.movieapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MovieFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private ScrollView svMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous movie selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        svMovie = (ScrollView) inflater.inflate(R.layout.movie_view, container, false);
        return svMovie;
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the movie information.
        Bundle args = getArguments();
        if (args != null) {
            // Set movie based on argument passed in
            updateMovieView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set movie based on saved instance state defined during onCreateView
            updateMovieView(mCurrentPosition);
        }
    }

    public void updateMovieView(int position) {

        TextView movieTitle = (TextView) getView().findViewById(R.id.movieTitle);
        movieTitle.setText(ViewElements.Titles[position]);

        ImageView moviePoster = (ImageView) getView().findViewById(R.id.moviePoster);
        GlideApp.with(this).load("https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg").into(moviePoster);

        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current movie selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}