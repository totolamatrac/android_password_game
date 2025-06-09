package com.example.fiiirst.fragments;

import android.os.Bundle;

import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiiirst.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private static final String ARG_VALID = "isValid";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";

    private final ObservableBoolean validObs = new ObservableBoolean(false);
    private String title;
    private String description;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String title, String description) {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        args.putBoolean(ARG_VALID, false);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    public void setValid(boolean valid) {
        this.validObs.set(valid);
    }

    public ObservableBoolean getValid() {
        return this.validObs;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            validObs.set(getArguments().getBoolean(ARG_VALID));
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }
}