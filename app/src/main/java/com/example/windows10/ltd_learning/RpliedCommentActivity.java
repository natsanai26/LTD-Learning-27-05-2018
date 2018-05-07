package com.example.windows10.ltd_learning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.mRecycler.CommentAdapter;
import com.example.windows10.ltd_learning.mRecycler.RepliedAdapter;
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
 * Created by Windows10 on 4/30/2018.
 */

public class RpliedCommentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private EditText edit_comment;
    private ImageView add;
    private TextView textDetail,username,date;
    private RepliedAdapter commentAdapter;
    private String url = "http://158.108.207.7:8090/elearning/dialogue?id=";
    private int id_dialougue,id_course,id_member;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replied_comment_activity);
        init();
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        id_course = sharedPreferences.getInt("id_course_comment",-1);
        id_member = sharedPreferences.getInt("id_comment",-1);
        Log.d("RPP","--->"+id_course);
        Intent intent = getIntent();
        id_dialougue = intent.getIntExtra("id_parent",-1);
        textDetail.setText(intent.getStringExtra("text_detail"));
        username.setText(intent.getStringExtra("username_parent"));
        date.setText(intent.getStringExtra("string_date"));
        getCommentById(url+id_dialougue);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddComment(id_member,id_course,id_dialougue,"\""+edit_comment.getText().toString()+"\"");
            }
        });


    }
    public void init(){
        edit_comment = (EditText)findViewById(R.id.edit_comment);
        add = (ImageView) findViewById(R.id.add_reply);
        textDetail = (TextView) findViewById(R.id.textDetail);
        username =(TextView) findViewById(R.id.user_name);
        date = (TextView)findViewById(R.id.date);
        toolbar = (Toolbar) findViewById(R.id.toolbar_comment);
        toolbar.setTitle("Reply");
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void AddComment(int user_id,int course_id,int parent_id,String msg){
        final ElearningAPI elearningAPI = MyAPI.getAPI();
        Log.d("Retro","+++++"+user_id+" "+course_id+" "+parent_id+" "+msg);
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"msg\":%s}", msg));
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (jsonObject).toString());
            Call<ResponseBody> response = elearningAPI.addRepliedComment(user_id,course_id,parent_id,body);
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
                        Log.d("Retro", "RetroFit2.0 : " +e);
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


    public void getCommentById(String url){
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
//            java.lang.reflect.Type collectionType = new TypeToken<Collection<Comment>>() {}.getType();
//            List<Comment> commentResult =  new Gson().fromJson( jsonString , collectionType);
              Gson gson = new Gson();
              SingleDialogue commentResult = gson.fromJson(jsonString,SingleDialogue.class);
//            RateModel data = gson.fromJson(jsonString,RateModel.class);
//            List<RateModel.ResultBean> rateResult = data.getResult();
//            java.lang.reflect.Type collectionType = new TypeToken<Collection<RateModel>>() {}.getType();
//            List<RateModel> rateResult =  new Gson().fromJson( jsonString , collectionType);
            Log.d("JSONRP","check "+commentResult);
            List<SingleDialogue.SubdialoguesBean> replie_comment = null;
            if(commentResult.getSubdialogues().size() != 0) {
                replie_comment = commentResult.getSubdialogues();
                Log.d("JSONRP", "check " + replie_comment);

                commentAdapter = new RepliedAdapter(RpliedCommentActivity.this, replie_comment);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
                recyclerView.setLayoutManager(new LinearLayoutManager(RpliedCommentActivity.this));
                recyclerView.setAdapter(commentAdapter);
                recyclerView.setHasFixedSize(true);
            }else {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
                recyclerView.setLayoutManager(new LinearLayoutManager(RpliedCommentActivity.this));
                recyclerView.setAdapter(null);

            }

//            rv_comment.post(new Runnable() {
//                @Override
//                public void run() {
//                    rv_comment.smoothScrollToPosition(commentAdapter.getItemCount());
//
//                }
//            });

        } else {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
            recyclerView.setLayoutManager(new LinearLayoutManager(RpliedCommentActivity.this));
            recyclerView.setAdapter(null);
        }

    }

//    @Override
//    protected void onDestroy() {
//        Intent intent = new Intent(RpliedCommentActivity.this,CommentActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        super.onDestroy();
//    }
}
