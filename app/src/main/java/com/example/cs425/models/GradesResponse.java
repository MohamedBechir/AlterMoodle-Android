package com.example.cs425.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GradesResponse {

    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("courseid")
    @Expose
    private Integer courseid;
    @SerializedName("courseDesc")
    @Expose
    private String courseDesc;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("rawgrade")
    @Expose
    private String rawgrade;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRawgrade() {
        return rawgrade;
    }

    public void setRawgrade(String rawgrade) {
        this.rawgrade = rawgrade;
    }
}
