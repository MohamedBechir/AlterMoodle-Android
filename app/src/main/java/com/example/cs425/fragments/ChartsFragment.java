package com.example.cs425.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs425.R;
import com.example.cs425.models.Assignment;
import com.example.cs425.services.CoursesAssignments;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartsFragment extends Fragment {

    private static final String TAG = "ChartsFragment";

    PieChart pieChart;

    private Button gradesButton;

    public static final String coursesSettingsKey = "PREFS";
    public static final String courseKey = "COURSES";

    private ArrayList<Assignment> assignmentsDetails= new ArrayList<>();

    CoursesAssignments coursesAssignments = new CoursesAssignments();

    public ChartsFragment() {
    }


    public static ChartsFragment newInstance(String param1, String param2) {
        ChartsFragment fragment = new ChartsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        gradesButton = (Button) view.findViewById(R.id.gradesbtn);
        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: hh");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
            }
        });

        int totalNumberAssignments,finishedAssignments = 0, unfinishedAssignments = 0;

        totalNumberAssignments = coursesAssignments.calculateNumberOfAssignments(getActivity(), coursesSettingsKey, courseKey);

        assignmentsDetails = coursesAssignments.getAssignmentDetails(getActivity(), coursesSettingsKey, courseKey);

        for ( Assignment assignment : assignmentsDetails){
            if (!assignment.getStatus()){
                unfinishedAssignments += 1;
            }else {
                finishedAssignments += 1;
            }
        }

        TextView totalNumberAssignmentsTxt = (TextView) view.findViewById(R.id.totalnumberassignments);
        TextView finishedNumberAssignmentsTxt = (TextView) view.findViewById(R.id.finishedAss);
        TextView unfinishedNumberAssignmentsTxt = (TextView) view.findViewById(R.id.unfinishedAss);

        totalNumberAssignmentsTxt.setText("" + totalNumberAssignments);
        finishedNumberAssignmentsTxt.setText("" + finishedAssignments);
        unfinishedNumberAssignmentsTxt.setText("" + unfinishedAssignments);

        TextView totalNumberAssignmentsText = (TextView) view.findViewById(R.id.totalassignments);
        TextView finishedNumberAssignmentsText = (TextView) view.findViewById(R.id.finishedassignments);
        TextView unfinishedNumberAssignmentsText = (TextView) view.findViewById(R.id.unfinishedassignments);

        totalNumberAssignmentsText.setText("" + totalNumberAssignments);
        finishedNumberAssignmentsText.setText("" + finishedAssignments);
        unfinishedNumberAssignmentsText.setText("" + unfinishedAssignments);

        pieChart = (PieChart) view.findViewById(R.id.pie);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(finishedAssignments));
        entries.add(new PieEntry(unfinishedAssignments));
        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setColors(ColorTemplate.rgb("#FF0000"),ColorTemplate.rgb("#228B22"));

        PieData data = new PieData((dataSet));
        pieChart.setData(data);
        return view;
    }
}
