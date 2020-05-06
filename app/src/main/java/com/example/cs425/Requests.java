package com.example.cs425;

import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.LoginResponse;
import com.example.cs425.models.registerResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Requests {
    @POST ("login")
    Call<LoginResponse> LoginUser (@Body JSONObject object);


    @POST ("signup")
    Call<registerResponse> registerUser (@Body JSONObject object);


    @GET ("assignments")
    Call<List<AssignmentResponse>> getAssignments (@Header("authorization")String jwt);

}
