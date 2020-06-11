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
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
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
        getActivity().getSharedPreferences(coursesSettingsKey, 0).edit().clear().commit();
        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);
        // sets the end of class time (hour,minute)


        SharedPreferences preferences1 = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
        String jwtToken  = preferences1.getString("JWT_TOKEN",null);
        retrofitRequest request1 = new retrofitRequest();
        Call<CalendarResponse> CallableResponse1 = request1
                .retrofitRequest("http://10.0.2.2:3000/api/misc/")
                .getCalendar(jwtToken);
        CallableResponse1.enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                ArrayList<Schedule> schedules = new ArrayList<Schedule>();
                Schedule schedule = new Schedule();
                TimetableView timetableView = view.findViewById(R.id.timetable);
                for (Event event : response.body().getEvents()) {
                    schedule.setClassTitle(event.getTitle());
                    schedule.setStartTime(new Time(event.startHour((event.getStart()))
                            ,event.startMinute((event.getStart()))));
                    schedule.setEndTime(new Time(event.startHour((event.getEnd()))
                            ,event.startMinute((event.getEnd()))));
                    try {
                        Log.d(TAG, "onResponse: " + event.getDay(event.getStart()));
                        schedule.setDay(event.getDay(event.getStart()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    schedules.add(schedule);
                    timetableView.add(schedules);
                }
            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("JWT_TOKEN",null);
        retrofitRequest request = new retrofitRequest();
        Call<List<AssignmentResponse>> CallableResponse = request
                .retrofitRequest("http://10.0.2.2:3000/api/moodle/")
                .getAssignments(retrivedToken);
        CallableResponse.enqueue(new Callback<List<AssignmentResponse>>() {
            @Override
            public void onResponse(Call<List<AssignmentResponse>> call, Response<List<AssignmentResponse>> response) {

                SharedPreferences preferences = getActivity().getSharedPreferences("CS425", Context.MODE_PRIVATE);

                int numberAssignments = 0;
                int numberCourses = Integer.parseInt(preferences.getString("COURSESLENGTH", ""));
                for (AssignmentResponse j : response.body()){
                    if (j.getAssignment()!=null){
                        numberAssignments += j.getAssignment().size();
                    }
                }

                /*TextView totalnumbercourses = (TextView) view.findViewById(R.id.totalnumbercourses);
                totalnumbercourses.setText(""+numberCourses);
                TextView totalnumberass = (TextView) view.findViewById(R.id.totalnumberass);
                totalnumberass.setText(""+numberAssignments);*/

                //Saves courses and assignments info in the sharedPreferences

                SharedPreferences settings = getActivity().getSharedPreferences(coursesSettingsKey,0);
                SharedPreferences.Editor editor = settings.edit();
                String s = new Gson().toJson(response.body());
                editor.putString("COURSES", s);
                editor.apply();
                /*Log.d(TAG, "onCreateView: " + getActivity().getSharedPreferences(coursesSettingsKey,0)
                        .getString("COURSES", ""));*/
            }

            @Override
            public void onFailure(Call<List<AssignmentResponse>> call, Throwable t) {
                Log.d("courses","courses are : "+t.getMessage());
            }
        });



        return view;
    }
}
