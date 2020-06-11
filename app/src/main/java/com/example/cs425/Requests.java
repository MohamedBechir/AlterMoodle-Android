package com.example.cs425;

import com.example.cs425.models.AssignmentResponse;
import com.example.cs425.models.CalendarResponse;
import com.example.cs425.models.GradesResponse;
import com.example.cs425.models.LoginResponse;
import com.example.cs425.models.UrgentAssignments;
import com.example.cs425.models.registerResponse;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Requests {
    @POST ("login")
    Call<LoginResponse> LoginUser (@Body JSONObject object);


    @POST ("signup")
    Call<registerResponse> registerUser (@Body JSONObject object);


    @GET ("assignments")
    Call<List<AssignmentResponse>> getAssignments (@Header("authorization")String jwt);

    @GET ("grades")
    Call<List<GradesResponse>> getGrades (@Header("authorization")String jwt);

    @GET ("assignments/urgent")
    Call<List<UrgentAssignments>> getUrgentAssignments (@Header("authorization")String jwt);

    @GET ("calendar")
    Call<CalendarResponse> getCalendar (@Header("authorization")String jwt);

}
