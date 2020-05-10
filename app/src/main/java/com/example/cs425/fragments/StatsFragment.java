package com.example.cs425.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.models.GradesResponse;
import com.example.cs425.models.retrofitRequest;
import com.example.cs425.recyclerview.StatsRecyclerViewAdapter;
import com.example.cs425.services.Grades;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatsFragment extends Fragment {

    private static final String TAG = "StatsFragment";
    public static final String coursesSettingsKey = "PREFS";
    public static final String courseKey = "COURSES";
    public static final String gradesSettingsKey = "GRADES";
    public static final String gradeKey = "GRADESRESPONSE";


    private ArrayList<String> coursesCodes = new ArrayList<>();
    private ArrayList<String> coursesOverall = new ArrayList<>();
    private ArrayList<String> coursesNames= new ArrayList<>();

    RecyclerView recyclerView;

    Grades grades = new Grades();


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
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        initStats(view);
        return view;
    }

    private void initStats(View view) {
        SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("JWT_TOKEN", null);
        retrofitRequest request = new retrofitRequest();
        Call<List<GradesResponse>> CallableResponse = request
                .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
                .getGrades(retrivedToken);
        CallableResponse.enqueue(new Callback<List<GradesResponse>>() {
            @Override
            public void onResponse(Call<List<GradesResponse>> call, Response<List<GradesResponse>> response) {

                SharedPreferences settings = getActivity().getSharedPreferences(gradesSettingsKey, 0);
                SharedPreferences.Editor editor = settings.edit();
                String s = new Gson().toJson(response.body());
                editor.putString(gradeKey, s);
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


        grades.getGrades(getActivity(), gradesSettingsKey, gradeKey, coursesOverall);
        grades.getCoursesCodes(getActivity(), gradesSettingsKey, gradeKey, coursesCodes);
        grades.getCoursesNames(getActivity(), gradesSettingsKey, gradeKey, coursesNames);



        initRecyclerView(view, coursesCodes, coursesOverall, coursesNames);

    }

    public void initRecyclerView(View view, ArrayList<String> coursesCodes
            , ArrayList<String> courseOverall, ArrayList<String> coursesNames) {
        recyclerView = view.findViewById(R.id.recycler_view);
        StatsRecyclerViewAdapter adapter = new StatsRecyclerViewAdapter(getActivity(),
                coursesCodes, courseOverall, coursesNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
}
