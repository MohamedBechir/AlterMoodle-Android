package com.example.cs425.services;

import android.app.Activity;
import android.content.SharedPreferences;
import android.telecom.Call;
import android.util.Log;

import com.example.cs425.models.AssignmentResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class CoursesAssignments {

    //Converts Json String array to an ArrayList

    public ArrayList<AssignmentResponse> convertToList(String coursesAssingments){
        ArrayList<AssignmentResponse> coursesAssingmentsList = new Gson().
                fromJson(coursesAssingments.toString(), new TypeToken<List<AssignmentResponse>>(){}.getType());
        return coursesAssingmentsList;
    }

    //Gets the courses IDs
    public ArrayList<String> getCoursesID (Activity activity, String settingsKey, String courseKey, ArrayList<String> coursesIds){
        ArrayList<AssignmentResponse> assignmentResponses = convertToList(getPreferencesData(activity, settingsKey,courseKey));
        for (AssignmentResponse course : assignmentResponses){
            coursesIds.add(course.getCourseInfo().getCourseName());
        }
        return coursesIds;
    }


    //Gets the courses IDs
    public ArrayList<String> getCoursesNames (Activity activity, String settingsKey, String courseKey,  ArrayList<String> coursesNames){
        ArrayList<AssignmentResponse> assignmentResponses = convertToList(getPreferencesData(activity, settingsKey,courseKey));
        for (AssignmentResponse course : assignmentResponses){
            coursesNames.add(course.getId());
        }
        return coursesNames;
    }


    //Gets data saved in the sharedPreferences
    public String getPreferencesData (Activity activity, String settingsKey, String dataKey){
        SharedPreferences settings = activity.getSharedPreferences(settingsKey,0);
        String data = settings.getString(dataKey,"");
        return data;
    }
}
