package com.example.cs425.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.recyclerview.StatsRecyclerViewAdapter;
import com.example.cs425.recyclerview.TodoRecyclerViewAdapter;
import com.example.cs425.services.CoursesAssignments;

import java.util.ArrayList;


public class StatsFragment extends Fragment {

    private static final String TAG = "StatsFragment";
    public static final String coursesSettingsKey = "PREFS";
    public static final String courseKey = "COURSES";

    private ArrayList<String> coursesCodes = new ArrayList<>();
    private ArrayList<String> coursesNames = new ArrayList<>();
    private ArrayList<String> coursesOverall = new ArrayList<>();

    RecyclerView recyclerView;

    CoursesAssignments coursesAssignments = new CoursesAssignments();



    public StatsFragment() {
    }


    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_todo, container, false);
        initStats(view);
        return view;
    }

    private void initStats(View view) {
        coursesAssignments.getCoursesID(getActivity(),coursesSettingsKey,courseKey,coursesNames);

        coursesAssignments.getCoursesNames(getActivity(),coursesSettingsKey,courseKey,coursesCodes);

        coursesOverall.add("78/100");
        coursesOverall.add("85/100");
        coursesOverall.add("90/100");
        coursesOverall.add("96/100");


        initRecyclerView(view, coursesNames, coursesCodes, coursesOverall);

    }
    public void initRecyclerView(View view, ArrayList<String> coursesNames,
                                   ArrayList<String> coursesCodes, ArrayList<String> courseOverall) {
        recyclerView = view.findViewById(R.id.recycler_view);
        StatsRecyclerViewAdapter adapter = new StatsRecyclerViewAdapter(getActivity(), coursesNames,
                coursesCodes, courseOverall);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
