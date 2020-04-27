package com.example.cs425.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs425.LoginResponse;
import com.example.cs425.R;
import com.example.cs425.Requests;

import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email1 = (EditText) findViewById(R.id.email);
        EditText password1 = (EditText) findViewById(R.id.password);
        TextView errorLogin = (TextView) findViewById(R.id.error);

        //Move to sign up activity
        TextView textView = (TextView) findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        //Login verification and go to dahsbord
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString();
                String password = password1.getText().toString();

                OkHttpClient okHttpClient = new OkHttpClient();
                Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/api/user/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient);
                Retrofit retrofit = retrofitBuilder.build();
                Requests service = retrofit.create(Requests.class);
                JSONObject obj = new JSONObject();

                try {
                    obj.put("email", email);
                    obj.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"entered credentials are : "+ obj);

                //Credentials user = new Credentials(email,password);
                Call<LoginResponse> CallableResponse = service.LoginUser(obj);
                CallableResponse.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() == 200) {
                            try {
                                Log.d(TAG, "User credentials are : " + response.body().formattedResult());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            try {
                                JSONObject JSONError = new JSONObject(response.errorBody().string());
                                errorLogin.setText(JSONError.getString("message") + "*");
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });
    }
}
