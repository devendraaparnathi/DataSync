package com.example.datasync.Api;

import com.example.datasync.Pojo.SQLitePojo;
import com.example.datasync.Pojo.UserDataPojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getData")
    Call<ArrayList<UserDataPojo>>getDataList();

    @POST("saveData")
    Call<UserDataPojo>createUser(@Body UserDataPojo userDataPojo);

    @POST("saveList")
    Call<SQLitePojo> postData(@Body SQLitePojo sqLitePojo);


}
