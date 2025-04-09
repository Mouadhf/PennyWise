package com.example.pennywise.APIbackend;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BackEndClientService {
    @POST("/users/login")
    Call<Boolean> login(@Query("username") String username, @Query("password") String password);

    @POST("/users/signup")
    Call<String> signup(@Query("username") String username, @Query("password") String password);
}
