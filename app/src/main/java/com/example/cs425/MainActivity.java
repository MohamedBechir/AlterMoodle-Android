package com.example.cs425;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Account
        TextView textView = (TextView) findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
               startActivity(intent);
           }
       });

        //Login

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

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
                    obj.put("password",password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"user is : "+ obj);

                //Credentials user = new Credentials(email,password);
                Call<LoginResponse> CallableResponse = service.LoginUser(obj);

                CallableResponse.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code()==200){
                            Intent intent = new Intent(MainActivity.this,DashboardActivity.class );
                            startActivity(intent);
                        }else {
                        Toast.makeText(MainActivity.this,"Wrong credentials",Toast.LENGTH_LONG).show();
                    }}

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d(TAG,t.getMessage());

                    }
                });
}
        });
    }

}
