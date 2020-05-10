package com.example.cs425.services;

import android.app.Activity;
import android.util.Log;

import com.example.cs425.models.Assignment;
import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.GradesResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Grades {

    CoursesAssignments coursesAssignments = new CoursesAssignments();

    public ArrayList<GradesResponse> convertToListGrades(String grades){
        ArrayList<GradesResponse> gradesResponses = new Gson().
                fromJson(grades.toString(), new TypeToken<List<GradesResponse>>(){}.getType());
        return gradesResponses;
    }

    public ArrayList<String> getGrades (Activity activity, String settingsKey, String courseKey, ArrayList<String> coursesOverall){
        ArrayList<GradesResponse> gradesResponses = convertToListGrades(coursesAssignments.getPreferencesData(activity, settingsKey,courseKey));

        for (GradesResponse grade : gradesResponses){
            coursesOverall.add(grade.getGrade());
        }
        return coursesOverall;
    }
    public ArrayList<String> getCoursesCodes (Activity activity, String settingsKey, String courseKey, ArrayList<String> coursesCodes){
        ArrayList<GradesResponse> gradesResponses = convertToListGrades(coursesAssignments.getPreferencesData(activity, settingsKey,courseKey));

        for (GradesResponse grade : gradesResponses){
            coursesCodes.add(grade.getCourseCode());
        }
        return coursesCodes;
    }
    public ArrayList<String> getCoursesNames (Activity activity, String settingsKey, String courseKey, ArrayList<String> coursesNames){
        ArrayList<GradesResponse> gradesNames = convertToListGrades(coursesAssignments.getPreferencesData(activity, settingsKey,courseKey));

        for (GradesResponse grade : gradesNames){
            coursesNames.add(grade.getCourseDesc());
        }
        return coursesNames;
    }
}
