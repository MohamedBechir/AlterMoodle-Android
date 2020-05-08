package com.example.cs425.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cs425.R;
import com.example.cs425.models.Assignment;
import com.example.cs425.services.CoursesAssignments;

import java.util.ArrayList;


public class AssignmentFragment extends Fragment {
    private static final String TAG = "AssignmentFragment";

    CoursesAssignments coursesAssignments = new CoursesAssignments();
    ArrayList <Assignment> assignmentsDetails;

    public static AssignmentFragment newInstance(String param1, String param2) {
        AssignmentFragment fragment = new AssignmentFragment();
        return fragment;
    }

    public AssignmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_assignment, container, false);

        Bundle bundle = getArguments();
        if (bundle!= null){
            String assignments = bundle.getString("ASSIGNMENT");
            assignmentsDetails = coursesAssignments.convertToListAssignment(assignments);
            //Log.d(TAG, "assignments are: " + assignmentsDetails);
        }
        return view ;
    }
}
