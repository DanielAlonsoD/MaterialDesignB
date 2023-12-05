package com.example.materialdesignb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.search.SearchBar;
import com.google.android.material.snackbar.Snackbar;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View elemento = inflater.inflate(R.layout.fragment_search, container, false);

        SearchBar searchBar = (SearchBar) elemento.findViewById(R.id.searchBar);

        return elemento;
    }
}