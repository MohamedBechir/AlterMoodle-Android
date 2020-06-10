package com.example.cs425.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs425.R;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_profile, container, false); //pass the correct layout name for the fragment


        String  fullName = getArguments().getString("fullName");
        String email = getArguments().getString("email");
        String moodleToken = getArguments().getString("moodleToken");
        TextView fullNamefld = (TextView)  view.findViewById(R.id.fullName);
        TextView emailfld = (TextView) view.findViewById(R.id.emailfld);
        TextView moodleTokenfld = (TextView) view.findViewById(R.id.moodleTokenfld);

        fullNamefld.setText(fullName);
        emailfld.setText(email);
        moodleTokenfld.setText(moodleToken);
        return view;
    }
}
