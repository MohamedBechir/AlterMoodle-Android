package com.example.cs425.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs425.R;
import com.example.cs425.models.Assignment;
import com.example.cs425.recyclerview.AssignmentRecyclerViewAdapter;
import com.example.cs425.services.CoursesAssignments;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class ChartPerCourseFragment extends Fragment /*implements AssignmentRecyclerViewAdapter.OnAssignmentListener*/ {

    private static final String TAG = "AssignmentFragment";

    PieChart pieChart;


    public static ChartPerCourseFragment newInstance(String param1, String param2) {
        ChartPerCourseFragment fragment = new ChartPerCourseFragment();
        return fragment;
    }

    public ChartPerCourseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_chart_per_course, container, false);
        Button assignmentButton = (Button) view.findViewById(R.id.assignmentbtn);

        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AssignmentFragment()).commit();
            }
        });

        Bundle bundle = getArguments();
        String courseCode = bundle.getString("COURSE");
        String totalAssignments = bundle.getString("ASSIGNMENTS");


        String finished = bundle.getString("FINISHED");
        String unfinished = bundle.getString("UNFINISHED");

        int finishedInt = Integer.parseInt(finished);
        int unfinishedInt = Integer.parseInt(unfinished);


        TextView courseCodeV = (TextView) view.findViewById(R.id.courseCode);
        courseCodeV.setText(courseCode);

        TextView totalAssignmentsV = (TextView) view.findViewById(R.id.totalnumberass);
        totalAssignmentsV.setText("Total assignments: " + totalAssignments);

        TextView totalNumberAssignmentsText = (TextView) view.findViewById(R.id.totalassignmentspercourse);
        TextView finishedNumberAssignmentsText = (TextView) view.findViewById(R.id.finishedassignmentspercourse);
        TextView unfinishedNumberAssignmentsText = (TextView) view.findViewById(R.id.unfinishedassignmentspercourse);

        totalNumberAssignmentsText.setText("" + totalAssignments);
        finishedNumberAssignmentsText.setText("" + finished);
        unfinishedNumberAssignmentsText.setText("" + unfinished);

        pieChart = (PieChart) view.findViewById(R.id.pie);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(finishedInt));
        entries.add(new PieEntry(unfinishedInt));
        

        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setColors(ColorTemplate.rgb("#228B22"), ColorTemplate.rgb("#FF0000"));
        PieData data = new PieData((dataSet));
        pieChart.setData(data);

        return view ;
    }
}
