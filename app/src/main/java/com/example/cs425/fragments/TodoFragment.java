package com.example.cs425.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.DialogTitle;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodoFragment extends Fragment {
    private static final String TAG = "TodoFragment";
private ArrayList<String> coursesNames = new ArrayList<>() ;
private ArrayList<String> coursesIds = new ArrayList<>() ;


    public TodoFragment() {
    }


    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        initCourses(view);
        return view;
    }

private void initCourses (View view){
    SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
    String retrivedToken  = preferences.getString("JWT_TOKEN",null);
    retrofitRequest request = new retrofitRequest();
    Call<List<AssignmentResponse>> CallableResponse = request
            .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
            .getAssignments(retrivedToken);
    CallableResponse.enqueue(new Callback<List<AssignmentResponse>>() {
        @Override
        public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {
            for (AssignmentResponse course : response.body()){
                coursesNames.add(course.getCourseInfo().getCourseName());
                coursesIds.add(course.getId());
            }
            initRecyclerView(view,coursesNames,coursesIds);
        }

        @Override
        public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
            Log.d("courses","courses are : "+t.getMessage());
        }
    });
}

    private  void initRecyclerView(View view, ArrayList<String> coursesNames, ArrayList<String> coursesIds) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(coursesNames, coursesIds, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


}
