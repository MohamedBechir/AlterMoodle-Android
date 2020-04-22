package com.example.cs425;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Requests {
    @POST ("login")
    Call<LoginResponse> LoginUser (@Body JSONObject object);
}
