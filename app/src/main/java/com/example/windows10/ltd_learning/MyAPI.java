package com.example.windows10.ltd_learning;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by natsanai on 3/19/2018.
 */

public class MyAPI
{
    public static final String BASE_URL_COURSE_API = "http://158.108.207.7:8080/";
    public static final String BASE_URL_ELEARNNING = "http://158.108.207.7:8090/";

    public static ElearningAPI getAPI()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_ELEARNNING)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ElearningAPI.class);
    }
    public RequestBody getRequestBody(String jsonString)
    {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonString);
    }
}
