package com.example.cs425.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs425.R;
import com.example.cs425.models.registerResponse;
import com.example.cs425.models.retrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFinalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_final);

        //Create a link for the button register
        String firstName1 = getIntent().getStringExtra("firstName");
        String lastName1 = getIntent().getStringExtra("lastName");
        TextView email = (TextView) findViewById(R.id.emailR);
        TextView password = (TextView) findViewById(R.id.passwordR);
        TextView moodleToken = (TextView) findViewById(R.id.moodleToken);
        Button button = (Button) findViewById(R.id.register);
        TextView errorRegister = (TextView) findViewById(R.id.error);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String moodleToken1 = moodleToken.getText().toString();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("firstName", firstName1);
                    obj.put("lastName", lastName1);
                    obj.put("email", email1);
                    obj.put("password", password1);
                    obj.put("moodleToken", moodleToken1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                retrofitRequest request = new retrofitRequest();
                Call<registerResponse> CallableResponse = request
                        .retrofitRequest("http://10.0.2.2:3000/api/user/")
                        .registerUser(obj);

                CallableResponse.enqueue(new Callback<registerResponse>() {
                    @Override
                    public void onResponse(Call<registerResponse> call, Response<registerResponse> response) {
                        if (response.code() == 200) {
                            Intent intent = new Intent(RegisterFinalActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            try {
                                JSONObject JSONError = new JSONObject(response.errorBody().string());
                                String err = JSONError.getString("message");
                                errorRegister.setText(JSONError.getString("message") + "*");
                            } catch (Exception e) {
                                Toast.makeText(RegisterFinalActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<registerResponse> call, Throwable t) {
                        Log.d("TAG", t.getMessage());
                    }
                });
            }
        });
    }
}
