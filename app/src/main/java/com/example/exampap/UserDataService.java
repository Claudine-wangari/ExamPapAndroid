package com.example.exampap;

import com.example.exampap.data.model.LoggedInUser;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserDataService {

    @POST("/login")
    public LoggedInUser loginUser(@Body LoggedInUser loggedInUser);
}
