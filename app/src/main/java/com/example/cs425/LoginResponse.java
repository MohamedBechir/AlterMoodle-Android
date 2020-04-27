package com.example.cs425;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginResponse {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("moodleToken")
    @Expose
    private String moodleToken;
    @SerializedName("courses")
    @Expose
    private List<Integer> courses = null;
    @SerializedName("userToken")
    @Expose
    private String userToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMoodleToken() {
        return moodleToken;
    }

    public void setMoodleToken(String moodleToken) {
        this.moodleToken = moodleToken;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

   @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public JSONObject formattedResult () throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("email", getEmail());
        obj.put("firstName", getFirstName());
        obj.put("lastName", getLastName());
        obj.put("moodleToken", getMoodleToken());
        obj.put("courses", getCourses());
        obj.put("userToken", getUserToken());
        return obj;
    }}