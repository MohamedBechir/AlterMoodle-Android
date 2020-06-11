package com.example.cs425.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs425.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartPerCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartPerCourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "ChartPerCourseFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChartPerCourseFragment() {
    }

    public static ChartPerCourseFragment newInstance(String param1, String param2) {
        ChartPerCourseFragment fragment = new ChartPerCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_per_course, container, false);
        Button assignmentButton = (Button) view.findViewById(R.id.assignmentbtn);
        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: hh");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AssignmentFragment()).commit();
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            String totalAssignments = bundle.getString("TOTALNUMBER");
            String courseCode = bundle.getString("COURSECODE");
            //Log.d(TAG, "onCreateView: "+ courseCode);

            TextView courseCodeV = (TextView) view.findViewById(R.id.courseCode);
            courseCodeV.setText(courseCode);

            TextView totalAssignmentsV = (TextView) view.findViewById(R.id.totalnumberass);
            totalAssignmentsV.setText("Total assignments: " + totalAssignments);


        }
        return view;
    }
}
