package com.example.cs425.interceptor;

import com.example.cs425.Requests;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JwtInterceptor {

    public void retrofitRequest (String baseUrl){
        OkHttpClient.Builder okhtttpBuilder = new OkHttpClient.Builder();
        okhtttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder().header("Authorization","jwt-token");
                return chain.proceed(newRequest.build());
            }
        });

        /*Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhtttpBuilder.build());
        Retrofit retrofit = retrofitBuilder.build();
        Requests service = retrofit.create(Requests.class);
        return service;*/
    }
}
