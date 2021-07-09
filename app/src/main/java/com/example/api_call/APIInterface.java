package com.example.api_call;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/api/users?")
    Call<model> getmodels(@Query("page") String page);
}
