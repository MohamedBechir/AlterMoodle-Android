package com.example.cs425.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.activities.SideBarActivity;
import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.UrgentAssignments;
import com.example.cs425.models.retrofitRequest;
import com.example.cs425.recyclerview.AssignmentRecyclerViewAdapter;
import com.example.cs425.recyclerview.UrgentAssignmentRecyclerViewAdapter;
import com.example.cs425.services.CoursesAssignments;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UrgentAssignmentFragment extends Fragment {
    RecyclerView recyclerView;

    public static final String JWTSettingsKey = "CS425";
    public static final String JWTKey = "JWT_TOKEN";
    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<String> assignmentName = new ArrayList<>();
    ArrayList<String> remainingTime = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();

    Date currentTime = Calendar.getInstance().getTime();


    CoursesAssignments coursesAssignments = new CoursesAssignments();

    public UrgentAssignmentFragment() {
    }


    public static UrgentAssignmentFragment newInstance(String param1, String param2) {
        UrgentAssignmentFragment fragment = new UrgentAssignmentFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_urgent_assignment, container, false);
        initUrgentAssignments(view);
        return view ;
    }

    private void initUrgentAssignments(View view) {
        String retriedToken =coursesAssignments.getPreferencesData(getActivity(),JWTSettingsKey,JWTKey);
        retrofitRequest request = new retrofitRequest();
        Call<List<UrgentAssignments>> CallableResponse = request
                .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
                .getUrgentAssignments(retriedToken);

        CallableResponse.enqueue(new Callback<List<UrgentAssignments>>() {
            @Override
            public void onResponse(Call<List<UrgentAssignments>> call, Response<List<UrgentAssignments>> response) {
                for (UrgentAssignments urgentAssignments : response.body()){
                    courseName.add(urgentAssignments.getCourseCode());
                    assignmentName.add(urgentAssignments.getName());
                    url.add(urgentAssignments.getUrl());
                    remainingTime.add("Due date is: " + urgentAssignments.getExpDate());
                }
                initRecyclerView(view);
            }
            @Override
            public void onFailure(Call<List<UrgentAssignments>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });
    }
    public   void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.urgent_recycler_view);
        UrgentAssignmentRecyclerViewAdapter adapter = new UrgentAssignmentRecyclerViewAdapter(getActivity(), courseName,
                assignmentName, remainingTime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
