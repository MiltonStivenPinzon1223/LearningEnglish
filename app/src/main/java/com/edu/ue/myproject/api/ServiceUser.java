package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.Auth;
import com.edu.ue.myproject.model.ResponseCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceUser {
    @GET("users")
    public Call<ResponseCredentials> users(@Body Auth login);
    @PUT("users/12")
    public Call<ResponseCredentials> usersUpdate(@Body Auth login);
}
