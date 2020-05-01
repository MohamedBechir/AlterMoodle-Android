package com.example.cs425.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;


public class ProfileFragment extends Fragment {
    String fullName;
    String email;
    String moodleToken;


    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullName = getArguments().getString("fullName");
        email = getArguments().getString("email");
        moodleToken = getArguments().getString("moodleToken");

        Log.d("TAG","fullname is :"+ fullName);
        Log.d("TAG","email is :"+ email);
        Log.d("TAG","moodle token is :"+ moodleToken);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
