package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.Registration;
import com.edu.ue.myproject.model.ResponseCategories;
import com.edu.ue.myproject.model.ResponseResponse;
import com.edu.ue.myproject.model.ResponseResults;
import com.edu.ue.myproject.model.Results;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceResults {

    @GET("results")
    public Call<ResponseResults> results();

    @POST("results")
    public Call<ResponseResponse> registration(@Body Registration registration);
}
