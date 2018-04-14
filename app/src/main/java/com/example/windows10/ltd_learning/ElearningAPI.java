package com.example.windows10.ltd_learning;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Windows10 on 2/5/2018.
 */

public interface ElearningAPI {
    @POST("/elearning/course/addRegis}")
    Call<ResponseBody> addRegis(
            @Query("courseId") int courseId,
            @Query("memberId") int memberId
    );

    @POST("/elearning/course/addRating")
        //@Headers("Content-Type: application/json")
    Call<ResponseBody> addRating(
            @Query("courseId") int courseId,
            @Query("memberId") int memberId,
            @Body RequestBody params

    );

    @POST("/elearning/course/isRegis")
    Call<ResponseBody> isRegis(
            @Body RequestBody params
    );


    @POST("/elearning/member/login")
    Call<ResponseBody> login(
            @Body ResponseBody params
    );

    @PUT("/elearning/member/update")
    Call<ResponseBody> updateMember(
            @Body ResponseBody params
    );

    @PUT("/elearning/member/update")
    Call<ResponseBody> updatePassword(
            @Body ResponseBody params
    );

    @POST("/elearning/member/add")
    Call<ResponseBody> addMember(
            @Body ResponseBody params
    );

    @GET("/elearning/course")
    Call<ResponseBody> getCourseByStudentId(
            @Query("studentId") int studentId
    );

    @GET("/elearning/course")
    Call<ResponseBody> getCourseByName(
            @Query("name") String name
    );

    @GET("/elearning/course")
    Call<ResponseBody> getCourseByTeacherName(
            @Query("teacherName") String teacherName
    );
}