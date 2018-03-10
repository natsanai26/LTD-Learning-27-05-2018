package com.example.windows10.ltd_learning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.windows10.ltd_learning.mRecycler.MyCourse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows10 on 12/5/2017.
 */

public class CourseDetail extends AppCompatActivity implements OnPreparedListener {
    private VideoView videoView;
    private Button btn,btn2;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6;
    private SharedPreferences sp;
    private boolean enrolled;
    private String courseName;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private Button enroll;
    private TextView tx_name,tx_detail,testIdUser,testIdCourse;
    private int idUser,idCourse;
    private static String URL_isRegis = "http://158.108.207.7:8090/elearning/course/isRegis";
    private static String URL_addRegis ="http://158.108.207.7:8090/elearning/course/addRegis";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail_layout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setupVideoView();

        enroll = (Button) findViewById(R.id.button_enroll);
        testIdUser = (TextView) findViewById(R.id.testIdUser);
        testIdCourse = (TextView) findViewById(R.id.testIdCourse);
        tx_name = (TextView) findViewById(R.id.course_name);
        tx_detail = (TextView)findViewById(R.id.detail);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        idUser = sp.getInt("idMember",-1);
        Intent intent  = getIntent();
        idCourse = intent.getIntExtra("course_id",-2);
        testIdUser.setText("UserID : "+idUser);
        testIdCourse.setText("CourseID :"+idCourse);
        courseName = getIntent().getStringExtra("course_name");
        tx_name.setText("Course Name : "+getIntent().getStringExtra("course_name"));
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idUser == -1){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("check_for_enroll",true);
                    Toast.makeText(CourseDetail.this,"Please Register or Login First", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(CourseDetail.this,"Enroll Doing!!", Toast.LENGTH_LONG).show();
                    String dataJson = getRegister();
                    Intent intent = new Intent(CourseDetail.this,CourseDetailForMyCourse.class);
                    intent.putExtra("course_id",idCourse);
                    intent.putExtra("course_name",courseName);
                    enroll.setText("Enrolled");
                    enroll.setAlpha(.5f);
                    enroll.setClickable(false);
                    showSectionCourse();
                }

            }
        });
        if(idUser != -1){
            getStatusEnroll();
        }
    }
    private void setupVideoView() {
        // Make sure to use the correct VideoView import
        videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);

        //For now we just picked an arbitrary item to play
        videoView.setVideoURI(Uri.parse("http://158.108.207.7:8080/api/ts/key999/25/30/index.m3u8"));
    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        videoView.start();
    }


    private void showSectionCourse(){
        btn2 =(Button)findViewById(R.id.btn2);

        btn = (Button)findViewById(R.id.btn);
        txt1 =(TextView)findViewById(R.id.e1);
        txt2 = (TextView)findViewById(R.id.e2);
        txt3 = (TextView)findViewById(R.id.e3);
        txt4 = (TextView)findViewById(R.id.e4);
        txt5 = (TextView)findViewById(R.id.e5);
        txt6 = (TextView)findViewById(R.id.e6);

        final ViewGroup transition =(ViewGroup)findViewById(R.id.transition);
        btn.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(transition);
                visible = !visible;
                txt1.setVisibility(visible? View.VISIBLE:View.GONE);
                txt2.setVisibility(visible? View.VISIBLE:View.GONE);
                txt3.setVisibility(visible? View.VISIBLE:View.GONE);
                txt4.setVisibility(View.GONE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @RequiresApi (api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(transition);
                visible = !visible;
                txt5.setVisibility(visible? View.VISIBLE:View.GONE);
                txt6.setVisibility(visible? View.VISIBLE:View.GONE);
                txt4.setVisibility(View.GONE);
            }
        });

    }

    private void getStatusEnroll(){
        String json = POST2(URL_isRegis,idUser,idCourse);
        Log.d("JSON", "####From getStatus"+json);
        Gson gson = new Gson();
        IsRegis isRegis = gson.fromJson(json,IsRegis .class);
        Log.d("JSON", "####From getStatus2"+isRegis.isIsRegis());
        enrolled = isRegis.isIsRegis();
        SharedPreferences.Editor editor = sp.edit();
        Log.d("JSON", "####From getStatus3"+enrolled);
        editor.putBoolean("checkEnroll",enrolled);

        changButtonEnroll();
    }

    private void changButtonEnroll(){
        if(enrolled){
            enroll.setText("Enrolled");
            enroll.setAlpha(.5f);
            enroll.setClickable(false);
            showSectionCourse();
        }
    }

    private String getRegister(){
        String json = POST(URL_addRegis,idCourse,idUser);
        Log.d("JSON", "####From getRegister");
        return json;

    }
    private void getInformation() {
        Log.d("JSON", "####TestURL_FromCoursDetail" + URL_addRegis);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_addRegis)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddRegisAPI addRegisAPI = retrofit.create(AddRegisAPI.class);

        HashMap<String,String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        Call<ResponseBody> call = addRegisAPI.addRegis(headerMap,"addRegis",String.valueOf(idCourse),String.valueOf(idUser));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d("JSON", "onResponse: Server Response: " + response.toString());
                try{
                    String json = response.body().string();
                    Log.d("JSON", "onResponse: json: " + json);
                    JSONObject data = null;
                    data = new JSONObject(json);
//                            Log.d(TAG, "onResponse: data: " + data.optString("json"));

                }catch (JSONException e){
                    Log.e("JSON", "onResponse: JSONException: " + e.getMessage() );
                }catch (IOException e){
                    Log.e("JSON", "onResponse: JSONException: " + e.getMessage() );
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("JSON", "onFailure: Something went wrong: " + t.getMessage() );
                Toast.makeText(CourseDetail.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });



//        Uri.Builder builder = new Uri.Builder();
//        String host = "158.108.207.7:8090";
//        builder.scheme("https")
//                .encodedAuthority(host)
//                .appendPath("elearning")
//                .appendPath("course")
//                .appendPath("addRegis");
//        String myUrl = builder.build().toString();
//        Log.d("JSON", "####TestURL_++" + myUrl);



    }
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,myUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                    Toast.makeText(getApplication(),response,Toast.LENGTH_SHORT).show();
//                Log.d("JSON", "####Success!!");
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(CourseDetail.this,error+"",Toast.LENGTH_SHORT).show();
//                        Log.d("JSON", "####Error...");
//                    }
////                }){
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String ,String> params = new HashMap<String, String>();
////                String course_id = String.valueOf(idCourse);
////                String user_id = String.valueOf(idUser);
////                params.put("courseId",course_id);
////                params.put("memberId",user_id);
////                return params;
////            }
//        };
//        MySingleton.getInstance(this).addToReauestQue(stringRequest);
//
//    }
public static String POST(String url,int courseId,int memberId){
    InputStream inputStream = null;
    String result = "";
    try {

        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(url);

        String json = "";

        // 3. build jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("courseId", courseId);
        jsonObject.accumulate("memberId", memberId);


        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person);

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);

        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream);

        else
            result = "Did not work!";

    } catch (Exception e) {
        Log.d("InputStream", e.getLocalizedMessage());
    }

    // 11. return result
    return result;
}
    public static String POST2(String url,int memberId,int courseId){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("memberId", memberId);
            jsonObject.accumulate("courseId", courseId);


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("courseId", String.valueOf(idCourse));
//        params.put("memberId", String.valueOf(idUser));
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_addRegis, params,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Log.d("JSON", "### Success!!");
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                        Log.d("JSON", "###JSON Error");
//            }
//        }
//        );
//        requestQueue.add(jsObjRequest);
//    }


//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest strRequest = new StringRequest(Request.Method.POST, URL_addRegis,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                        Log.d("JSON", "### Success!!");
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("JSON", "###JSON Error");
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("courseId",String.valueOf(idCourse));
//                params.put("memberId",String.valueOf(idUser));
//                return params;
//            }
//        };
//
//        queue.add(strRequest);
//

//        String json = POST(URL_addRegis,String.valueOf(idCourse).toString(),String.valueOf(idUser).toString());
//        if(!json.equals("")){
//
//            Log.d("JSON","Success!!");
//        }
//        else {
//            Log.d("JSON","Error JSON");
//        }
//
//        Log.d("JSON", "####TestURL_FromCoursDetail");





