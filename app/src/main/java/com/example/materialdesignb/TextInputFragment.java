package com.example.materialdesignb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;


public class TextInputFragment extends Fragment {

    public TextInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View elemento = inflater.inflate(R.layout.fragment_text_input, container, false);


        TextInputLayout textInputLayout = (TextInputLayout) elemento.findViewById(R.id.textInput);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) elemento.findViewById(R.id.AutocompleteText);

        String[] nombres = {"Pablo","Juan","Freddy","Lucia","Pedro","Santiago","Otros"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.layout_dropdown_personalizado, nombres);
        autoComplete.setAdapter(adapter);


        return elemento;
    }
}