package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.ResponseUsers;
import com.edu.ue.myproject.model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface ServiceUsers {
    @GET("users")
    public Call<ResponseUsers> users();
    @PUT("users/11")
    public Call<ResponseUsers> usersUpdate(@Body Users user);

}
