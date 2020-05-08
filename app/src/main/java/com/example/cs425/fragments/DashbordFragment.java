package com.example.cs425.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs425.R;
import com.example.cs425.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashbordFragment extends Fragment {
    private static final String TAG = "DashbordFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public DashbordFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("JWT_TOKEN",null);
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
