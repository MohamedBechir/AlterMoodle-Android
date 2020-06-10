package com.example.cs425.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UrgentAssignments {
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("expDate")
    @Expose
    private String expDate;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("duration")
    @Expose
    private Integer duration;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


}
