package com.example.cs425;

import com.example.cs425.models.LoginResponse;
import com.example.cs425.models.registerResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Requests {
    @POST ("login")
    Call<LoginResponse> LoginUser (@Body JSONObject object);


    @POST ("signup")
    Call<registerResponse> registerUser (@Body JSONObject object);

}
