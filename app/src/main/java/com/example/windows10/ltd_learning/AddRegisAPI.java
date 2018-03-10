package com.example.windows10.ltd_learning;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Windows10 on 2/5/2018.
 */

public interface AddRegisAPI {
    String BASE_URL = "158.108.207.7:8090/elearning/course/";
    @POST("{name_api}")
    Call<ResponseBody> addRegis(
            @HeaderMap Map<String,String> header,
            @Path("name_api") String nameAPI, //addRegis
            @Query("courseId") String courseId, //?courseId=25
            @Query("memberId") String memberId  //&memberId=138
            );
}
