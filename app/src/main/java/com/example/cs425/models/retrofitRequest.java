package com.example.cs425.models;

import com.example.cs425.Requests;

import org.json.JSONObject;


import java.util.function.Function;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitRequest {

    public Requests retrofitRequest (String baseUrl){
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        Retrofit retrofit = retrofitBuilder.build();
        Requests service = retrofit.create(Requests.class);
        return service;
    }
}
