package com.example.cs425.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.cs425.models.LoginResponse;
import com.example.cs425.models.retrofitRequest;
import com.example.cs425.R;


import org.json.JSONException;
import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Error";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
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
                JSONObject obj = new JSONObject();
                try {
                    obj.put("email", email);
                    obj.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                retrofitRequest request = new retrofitRequest();
                Call<LoginResponse> CallableResponse = request
                        .retrofitRequest("http://10.0.2.2:3000/api/user/")
                        .LoginUser(obj);
                CallableResponse.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() == 200) {
                            try {
                                SharedPreferences preferences = getSharedPreferences("CS425",MODE_PRIVATE);
                                preferences.edit()
                                        .putString("JWT_TOKEN",response.body().formattedResult().getString("userToken"))
                                        .apply();
                                Log.d("HHHHHHH","hhhh"+preferences.getString("JWT_TOKEN","null"));

                               Intent intent = new Intent(MainActivity.this, SideBarActivity.class);
                                intent.putExtra("fullName",response.body().formattedResult()
                                        .getString("fullName"));
                                intent.putExtra("email",response.body().formattedResult()
                                        .getString("email"));
                                intent.putExtra("moodleToken",response.body().formattedResult().
                                        getString("moodleToken"));
                                startActivity(intent);
                                //Log.d(TAG, "User credentials are : " + response.body().formattedResult());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
