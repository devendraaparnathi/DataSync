package com.example.datasync.Api;

import com.example.datasync.UserDataPojo;
import com.example.datasync.model.SQLiteModelClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("saveData")
    Call<UserDataPojo>createUser(@Body UserDataPojo userDataPojo);

}
