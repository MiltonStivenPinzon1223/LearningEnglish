package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.Auth;
import com.edu.ue.myproject.model.ResponseCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ServiceLogin {
    @POST("auth/login")
    public Call<ResponseCredentials> accessLogin(@Body Auth login);

    @POST("auth/register")
    public Call<ResponseCredentials> register(@Body Auth login);
}
