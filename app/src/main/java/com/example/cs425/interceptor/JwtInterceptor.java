package com.example.cs425.interceptor;

import com.example.cs425.Requests;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    }
}
