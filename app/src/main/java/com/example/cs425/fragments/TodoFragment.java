package com.example.cs425.fragments;

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
import com.example.cs425.models.Assignment;
import com.example.cs425.recyclerview.TodoRecyclerViewAdapter;
import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.retrofitRequest;
import com.example.cs425.services.CoursesAssignments;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodoFragment extends Fragment implements TodoRecyclerViewAdapter.OnCourseListener {

    private static final String TAG = "TodoFragment";
    public static final String coursesSettingsKey = "PREFS";
    public static final String courseKey = "COURSES";
    public static final String JWTSettingsKey = "CS425";
    public static final String JWTKey = "JWT_TOKEN";

    CoursesAssignments coursesAssignments = new CoursesAssignments();

    private ArrayList<String> coursesNames = new ArrayList<>();
    private ArrayList<String> coursesIds = new ArrayList<>();
    private ArrayList<String> assignmentIds = new ArrayList<>();

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


                for (AssignmentResponse assignment: response.body()) {
                    assignmentIds.add(assignment.getId());
                }

                coursesAssignments.getCoursesID(getActivity(),coursesSettingsKey,courseKey,coursesNames);

                coursesAssignments.getCoursesNames(getActivity(),coursesSettingsKey,courseKey,coursesIds);

                initRecyclerView(view, coursesNames, coursesIds, assignmentIds);
            }
            @Override
            public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });
    }

    //Initiates the Recycler View
    public   void initRecyclerView(View view, ArrayList<String> coursesNames, ArrayList<String> coursesIds, ArrayList<String> assignmentIds) {
        recyclerView = view.findViewById(R.id.recycler_view);
        TodoRecyclerViewAdapter adapter = new TodoRecyclerViewAdapter(coursesNames, coursesIds, getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
    @Override
    public void onCourseClick(int position) {
        String clicked = coursesNames.get(position);
        String courseCode = coursesIds.get(position);
        String assignmentId = assignmentIds.get(position);

        String assignmentDetails = new Gson()
                .toJson(coursesAssignments.getAssignmentDetailsForEachCourse(getActivity(),coursesSettingsKey,courseKey,clicked));
        int totalAssignments = coursesAssignments
                .calculateNumberOfAssignmentsForEachCourse(getActivity(),coursesSettingsKey,courseKey,clicked);
        String totalAssignmentsStr = String.valueOf(totalAssignments);

        AssignmentFragment assignmentFragment = new AssignmentFragment();

        Bundle bundle = new Bundle();
        bundle.putString("COURSECODE", courseCode);
        bundle.putString("TOTALNUMBER", totalAssignmentsStr);
        bundle.putString("ASSIGNMENT", assignmentDetails);
        bundle.putString("ASSIGNMENTID", assignmentId);

        assignmentFragment.setArguments(bundle);

       getFragmentManager().beginTransaction().replace(R.id.fragment_container,assignmentFragment).commit();
    }
}
