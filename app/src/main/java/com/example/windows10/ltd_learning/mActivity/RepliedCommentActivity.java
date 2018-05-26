package com.example.windows10.ltd_learning.mActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.mInterface.MyAPI;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mModel.SingleDialogue;
import com.example.windows10.ltd_learning.mRecycler.RepliedAdapter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Windows10 on 4/30/2018.
 */

public class RepliedCommentActivity extends AppCompatActivity {
    private  boolean checkEnroll;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private EditText edit_comment;
    private ImageView add;
    private TextView textDetail,username,date;
    private RepliedAdapter commentAdapter;
    private int id_dialougue,id_course,id_member;
    private ElearningAPI elearningAPI;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replied_comment_activity);
        elearningAPI = MyAPI.getAPI();
        init();
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        id_course = sharedPreferences.getInt("id_course_comment",-1);
        id_member = sharedPreferences.getInt("id_comment",-1);
        checkEnroll = sharedPreferences.getBoolean("checkEnroll",false);
        Log.d("RPP","--->"+id_course);
        Intent intent = getIntent();
        id_dialougue = intent.getIntExtra("id_parent",-1);
        textDetail.setText(intent.getStringExtra("text_detail"));
        username.setText(intent.getStringExtra("username_parent"));
        date.setText(intent.getStringExtra("string_date"));
        getCommentById(id_dialougue);
        if (!checkEnroll) {
            edit_comment.setEnabled(false);

        }else {
            edit_comment.setEnabled(true);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEnroll){
                    if (!(edit_comment.getText().toString().trim().equals(""))){
                        AddComment(id_member,id_course,id_dialougue,"\""+edit_comment.getText().toString()+"\"");
                    }else {
                            Toast.makeText(RepliedCommentActivity.this,"Text is Empty.",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RepliedCommentActivity.this,"Please enroll this course.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCommentById(id_dialougue);

            }
        });


    }
    public void init(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
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


    public void getCommentById(int id){
        /*new AsyncTask<String, Void, String>() {
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
        }.execute(url);*/
        Call<ResponseBody> responseBody = elearningAPI.getCommentById(id);
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = response.body().string();
                    showData(jsonString);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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

                commentAdapter = new RepliedAdapter(RepliedCommentActivity.this, replie_comment);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
                recyclerView.setLayoutManager(new LinearLayoutManager(RepliedCommentActivity.this));
                recyclerView.setAdapter(commentAdapter);
                recyclerView.setHasFixedSize(true);
            }else {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
                recyclerView.setLayoutManager(new LinearLayoutManager(RepliedCommentActivity.this));
                recyclerView.setAdapter(null);

            }


        } else {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_replied);
            recyclerView.setLayoutManager(new LinearLayoutManager(RepliedCommentActivity.this));
            recyclerView.setAdapter(null);
        }

    }
}
