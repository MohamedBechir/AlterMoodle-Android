package com.example.cs425.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.RecyclerViewAdapter;
import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.retrofitRequest;
import com.example.cs425.services.CoursesAssignments;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodoFragment extends Fragment implements RecyclerViewAdapter.OnCourseListener {

    private static final String TAG = "TodoFragment";
    public static final String coursesSettingsKey = "PREFS";
    public static final String courseKey = "COURSES";
    public static final String JWTSettingsKey = "CS425";
    public static final String JWTKey = "JWT_TOKEN";

    CoursesAssignments coursesAssignments = new CoursesAssignments();

    private ArrayList<String> coursesNames = new ArrayList<>();
    private ArrayList<String> coursesIds = new ArrayList<>();

    RecyclerView recyclerView;


    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
        return fragment;
    }
    public TodoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_todo, container, false);
        initCourses(view);
        return view;
    }


    public void initCourses (View view){
        String retriedToken =coursesAssignments.getPreferencesData(getActivity(),JWTSettingsKey,JWTKey);
        retrofitRequest request = new retrofitRequest();
        Call<List<AssignmentResponse>> CallableResponse = request
                .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
                .getAssignments(retriedToken);

        CallableResponse.enqueue(new Callback<List<AssignmentResponse>>() {
            @Override
            public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {

                //Saves courses and assignments info in the sharedPreferences
                SharedPreferences settings = getActivity().getSharedPreferences(coursesSettingsKey,0);
                SharedPreferences.Editor editor = settings.edit();
                String s = new Gson().toJson(response.body());
                editor.putString("COURSES", s);
                editor.apply();

                coursesAssignments.getCoursesID(getActivity(),coursesSettingsKey,courseKey,coursesNames);

                coursesAssignments.getCoursesNames(getActivity(),coursesSettingsKey,courseKey,coursesIds);

                initRecyclerView(view, coursesNames, coursesIds);
            }
            @Override
            public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });
    }

    //Initiates the Recycler View
    public   void initRecyclerView(View view, ArrayList<String> coursesNames, ArrayList<String> coursesIds) {
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(coursesNames, coursesIds, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
    @Override
    public void onCourseClick(int position) {
        String clicked = coursesNames.get(position);
        String assignmentDetails = new Gson()
                .toJson(coursesAssignments.getAssignmentDetails(getActivity(),coursesSettingsKey,courseKey,clicked));

        AssignmentFragment assignmentFragment = new AssignmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ASSIGNMENT", assignmentDetails);
        assignmentFragment.setArguments(bundle);

       getFragmentManager().beginTransaction().replace(R.id.fragment_container,assignmentFragment).commit();
    }
}
