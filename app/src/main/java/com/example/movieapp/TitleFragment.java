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

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TitleFragment extends ListFragment {

    OnTitleSelectedListener mCallback;
    private MoviesRepository moviesRepository;
    private List<Movie> asdf;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnTitleSelectedListener {
        /** Called by TitleFragment when a list item is selected */
        public void onMovieSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviesRepository = MoviesRepository.getInstance();

        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                String[] s = new String[100];

                for(int i = 0; i < movies.size(); i++){
                    s[i]= movies.get(i).getTitle();
                }

                asdf = movies;

                int layout = android.R.layout.simple_list_item_activated_1;
                setListAdapter(new ArrayAdapter<String>(getActivity(), layout, s));
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.movie_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnTitleSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTitleSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onMovieSelected(asdf.get(position).getId());
        
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(asdf.get(position).getId(), true);
    }
}