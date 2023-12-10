package com.edu.ue.myproject.api;

import com.edu.ue.myproject.model.Auth;
import com.edu.ue.myproject.model.Categories;
import com.edu.ue.myproject.model.ResponseCategories;
import com.edu.ue.myproject.model.ResponseCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ServiceCategories {

    @GET("results/category")
    public Call<ResponseCategories> categories();
}
