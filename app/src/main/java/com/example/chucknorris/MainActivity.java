package com.example.chucknorris;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chucknorris.Entities.Joke;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //private String API_URL = "https://api.chucknorris.io/jokes/random?category=dev";
    private Button jokeButton;
    private TextView jokeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeButton = findViewById(R.id.button);
        jokeTv = findViewById(R.id.textView);

        //create Retrofit instance & parse the retrieved JSON using GSON deserializer
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io").addConverterFactory(GsonConverterFactory.create()).build();

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get a service and call object for the request
                JokeService service = retrofit.create(JokeService.class);
                Call<Joke> jokesCall = service.getJoke();
                jokesCall.enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        Joke j = response.body();
                        jokeTv.setText(j.getValue());
                    }
                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        System.out.println("failed");

                    }

                });

            }
        });
    }
}

