package com.example.httprequestwithokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_viewResult);


        OkHttpClient client = new OkHttpClient();

        String url = "https://reqres.in/api/users?page=2";


        final Request  request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
             if (response.isSuccessful()){

                 final String myResponse = response.body().string();

                 MainActivity.this.runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         textView.setText(myResponse);
                     }
                 });
             }

            }
        });
    }
}