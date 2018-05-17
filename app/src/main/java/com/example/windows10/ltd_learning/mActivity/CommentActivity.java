package com.example.windows10.ltd_learning.mActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.MyAPI;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mModel.Comment;
import com.example.windows10.ltd_learning.mRecycler.CommentAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Windows10 on 4/24/2018.
 */

public class CommentActivity extends AppCompatActivity {
    private CommentAdapter commentAdapter;
    private String URL_GET_Comment_idCourse = "http://158.108.207.7:8090/elearning/dialogue?courseId=";
    private RecyclerView rv_comment;
    private Toolbar toolbar;
    private ImageView add;
    private int id_member,id_course;
    private EditText edit_add;
    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = getIntent();
        id_course = intent.getIntExtra("course_id",1);
        id_member = intent.getIntExtra("member_id",1);
        Log.d("JSON#",""+id_course);
        getComment(URL_GET_Comment_idCourse+String.valueOf(id_course));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddComment(id_member,id_course,"\""+edit_add.getText().toString()+"\"");
            }
        });
    }
    public void init(){
        edit_add =(EditText) findViewById(R.id.edit_comment);
        add = (ImageView) findViewById(R.id.add_comment);
        rv_comment = (RecyclerView) findViewById(R.id.rv_comment);
//        ListView listView = (ListView)findViewById(R.id.rv_comment);
        toolbar = (Toolbar) findViewById(R.id.toolbar_comment);
        toolbar.setTitle("Comment");
    }
    public void AddComment(int user_id,int course_id,String msg){
        final ElearningAPI elearningAPI = MyAPI.getAPI();
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"msg\":%s}", msg));
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (jsonObject).toString());
            Call<ResponseBody> response = elearningAPI.addComment(user_id,course_id,body);
            Log.d("Retro", "RetroFit2.0 : " +response);
//            Call<ResponseBody> response = elearningAPI.addRating(courseId,memberId,body);

            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                    try {
                        //get your response....
                        Log.d("Retro", "RetroFit2.0 : " + rawResponse.body().string());
                        finish();
                        startActivity(getIntent());
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                    Log.d("Retro", "Request Fail");
                }
            });
        } catch (JSONException e) {
            Log.d("Retro", "Request Fail"+e);
        }

    }


    public void getComment(String url){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... url) {
                String result = "";
                try {

                    HttpGet httpGet = new HttpGet(url[0]);
                    HttpClient client = new DefaultHttpClient();

                    HttpResponse response = client.execute(httpGet);

                    int statusCode = response.getStatusLine().getStatusCode();

                    if (statusCode == 200) {
                        InputStream inputStream = response.getEntity().getContent();
                        BufferedReader reader = new BufferedReader
                                (new InputStreamReader(inputStream));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result += line;
                        }
                    }

                } catch (ClientProtocolException e) {
                    Log.d("ClientProtocolException",e.getMessage());

                } catch (IOException e) {
                    Log.d("IOException",e.getMessage());
                }
                return result;
            }
            protected void onPostExecute(String jsonString)  {
                Log.d("JCOMMENT","++"+jsonString);
                showData(jsonString);
            }
        }.execute(url);
    }

    public void showData(String jsonString)
    {
        if (!jsonString.equals("")) {
//            Log.d("SPILT","check "+jsonString);
//            int intIndex = jsonString.indexOf("\"iddialogue\"");
//            boolean check_subdialog;
//            Log.d("SPILT","check "+intIndex);
//            if(intIndex == - 1) {
//                check_subdialog = false;
//            } else {
//                check_subdialog = true;
//            }
            java.lang.reflect.Type collectionType = new TypeToken<Collection<Comment>>() {}.getType();
            List<Comment> commentResult =  new Gson().fromJson( jsonString , collectionType);

//            RateModel data = gson.fromJson(jsonString,RateModel.class);
//            List<RateModel.ResultBean> rateResult = data.getResult();
//            java.lang.reflect.Type collectionType = new TypeToken<Collection<RateModel>>() {}.getType();
//            List<RateModel> rateResult =  new Gson().fromJson( jsonString , collectionType);
            Log.d("JSON","check cmAll "+commentResult);

            commentAdapter = new CommentAdapter(CommentActivity.this, commentResult);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
            recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
            recyclerView.setAdapter(commentAdapter);
            recyclerView.setHasFixedSize(true);

            rv_comment.post(new Runnable() {
                @Override
                public void run() {
                    rv_comment.smoothScrollToPosition(commentAdapter.getItemCount());

                }
            });
//            ListView listView = (ListView)findViewById(R.id.rv_comment);
//            listView.setAdapter(new CommentAdapter2(CommentActivity.this,commentResult));

        } else {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
            recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
            recyclerView.setAdapter(null);
        }

    }

    @Override
    protected void onResume() {
        getComment(URL_GET_Comment_idCourse+String.valueOf(id_course));
        super.onResume();
    }
}
