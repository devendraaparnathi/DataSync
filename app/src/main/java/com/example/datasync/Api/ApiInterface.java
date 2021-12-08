package com.example.datasync.Api;

import com.example.datasync.model.SQLiteModelClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("saveData")
    Call<SQLiteModelClass>createUser(@Body SQLiteModelClass sqLiteModelClass);

}
