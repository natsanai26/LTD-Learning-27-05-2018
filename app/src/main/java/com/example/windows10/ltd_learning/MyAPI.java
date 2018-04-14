package com.example.windows10.ltd_learning;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by natsanai on 3/19/2018.
 */

public class MyAPI
{
    public static ElearningAPI getAPI()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://158.108.207.7:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ElearningAPI.class);
    }
}
