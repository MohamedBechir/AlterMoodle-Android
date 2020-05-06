package com.example.cs425.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.JsonWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashbordFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    com.example.cs425.models.retrofitRequest retrofitRequest = new retrofitRequest();
    private String mParam1;
    private String mParam2;

    public DashbordFragment() {
    }

    public static DashbordFragment newInstance(String param1, String param2) {
        DashbordFragment fragment = new DashbordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("JWT_TOKEN",null);
        //String jwtToken = "Bearer "+retrivedToken;
        //Log.d("TOKEN","this is the token : "+retrivedToken);
        retrofitRequest request = new retrofitRequest();
        Call<List<AssignmentResponse>> CallableResponse = request
                .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
                .getAssignments(retrivedToken);
        CallableResponse.enqueue(new Callback<List<AssignmentResponse>>() {
            @Override
            public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {
                int numberAssignments = 0;
                int numberCourses = 0;
                for (AssignmentResponse j : response.body()){
                   if (j.getAssignment()!=null){
                       numberAssignments += j.getAssignment().size();
                   }
                   numberCourses += 1;
                }
                Log.d("ASSIGNMENTS","number of assignments is : "+numberAssignments);
                Log.d("COURSES","number of courses is : "+numberCourses);

            }

            @Override
            public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_dashbord, container, false);
    }
}
