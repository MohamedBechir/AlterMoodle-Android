package com.example.cs425.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs425.R;
import com.example.cs425.models.Assignment;
import com.example.cs425.recyclerview.AssignmentRecyclerViewAdapter;
import com.example.cs425.services.CoursesAssignments;

import java.util.ArrayList;


public class AssignmentFragment extends Fragment /*implements AssignmentRecyclerViewAdapter.OnAssignmentListener*/ {
    private static final String TAG = "AssignmentFragment";
    RecyclerView recyclerView;


    CoursesAssignments coursesAssignments = new CoursesAssignments();

    ArrayList <Assignment> assignmentsDetails;
    private ArrayList<String> assignmentsName = new ArrayList<>();
    ArrayList<String> assignmentsDescription = new ArrayList<>();
    ArrayList<String> assignmentsDueDate = new ArrayList<>();
    ArrayList<String> assignmentsStatus = new ArrayList<>();

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
        initAssignments(view);
        return view ;
    }


    public void initAssignments(View view){

        Bundle bundle = getArguments();
        if (bundle!= null){
            String totalAssignments = bundle.getString("TOTALNUMBER");
            String courseCode = bundle.getString("COURSECODE");
            //Log.d(TAG, "onCreateView: "+ courseCode);

            TextView courseCodeV = (TextView) view.findViewById(R.id.courseCode);
            courseCodeV.setText(courseCode);

            TextView totalAssignmentsV = (TextView) view.findViewById(R.id.totalnumberass);
            totalAssignmentsV.setText("Total assignments: "+totalAssignments);


            String assignments = bundle.getString("ASSIGNMENT");
            assignmentsDetails = coursesAssignments.convertToListAssignment(assignments);

            for (Assignment assignment : assignmentsDetails){
                assignmentsName.add(assignment.getName());
                assignmentsDescription.add("Description: " +assignment.getDescription()
                        .substring(3,assignment.getDescription().length()-4));
                assignmentsDueDate.add("Due Date: " + assignment.getExpDate());
                if (assignment.getStatus() == true){
                    assignmentsStatus.add("Done");
                }else {
                    assignmentsStatus.add("Pending");
                }
            }
            //Log.d(TAG, "initAssignments: " + assignmentsName);

            initRecyclerView(view,assignmentsName);

        }
    }

    public   void initRecyclerView(View view, ArrayList<String> assignmentsName) {
        recyclerView = view.findViewById(R.id.recycler_view_ass);
        //Log.d(TAG, "initRecyclerView: "+ assignmentsName);
        AssignmentRecyclerViewAdapter adapter = new AssignmentRecyclerViewAdapter(getActivity(), assignmentsName,assignmentsDescription
                , assignmentsDueDate, assignmentsStatus/*, this*/);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    /*@Override
    public void OnAssignmentClick(int position) {
        Log.d(TAG, "OnAssignmentClick: clicked");

    }*/
}
