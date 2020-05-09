package com.example.cs425.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs425.R;
import com.example.cs425.models.*;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashbordFragment extends Fragment {
    private static final String TAG = "DashbordFragment";
    public static final String coursesSettingsKey = "PREFS";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public DashbordFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);
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

                TextView totalnumbercourses = (TextView) view.findViewById(R.id.totalnumbercourses);
                totalnumbercourses.setText(""+numberCourses);
                TextView totalnumberass = (TextView) view.findViewById(R.id.totalnumberass);
                totalnumberass.setText(""+numberAssignments);

                //Saves courses and assignments info in the sharedPreferences

                SharedPreferences settings = getActivity().getSharedPreferences(coursesSettingsKey,0);
                SharedPreferences.Editor editor = settings.edit();
                String s = new Gson().toJson(response.body());
                editor.putString("COURSES", s);
                editor.apply();
            }

            @Override
            public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });



        return view;
    }
}
