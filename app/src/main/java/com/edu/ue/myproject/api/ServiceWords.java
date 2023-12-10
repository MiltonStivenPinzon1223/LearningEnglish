package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.ResponseWords;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceWords {

    @GET("word")
    public Call<ResponseWords> word();
    @GET("words")
    public Call<ResponseWords> words();
}
