package com.example.datasync.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String baseurl="http://192.168.2.3:8080/contacts/";

    static Gson gson=new GsonBuilder()
            .setLenient()
            .create();

    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getRetrofit()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
