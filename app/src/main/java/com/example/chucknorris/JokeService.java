package com.example.chucknorris;

import com.example.chucknorris.Entities.Joke;

import retrofit2.http.GET;
import retrofit2.Call;


public interface JokeService {
    @GET("/jokes/random?category=dev")
    Call<Joke> getJoke();


}
