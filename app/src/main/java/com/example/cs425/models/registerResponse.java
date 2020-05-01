package com.example.cs425.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class registerResponse {
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

}
