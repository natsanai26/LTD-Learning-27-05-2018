package com.example.windows10.ltd_learning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

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

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRatingActivity extends AppCompatActivity {

    private final String URL_ADD_RATING = "http://158.108.207.7:8090/elearning/course/addRating";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        Intent intent = getIntent();
        final int memberId = intent.getIntExtra("member_id",-1);
        final int courseId = intent.getIntExtra("course_id",-1);
        final RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        Button button = (Button) findViewById(R.id.okButton);
        Button cbutton = (Button) findViewById(R.id.cancelButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //POST(URL_ADD_RATING,courseId,memberId,ratingBar.getRating());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://158.108.207.7:8090")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                AddRegisAPI addRatingAPI = retrofit.create(AddRegisAPI.class);
                try {
                    JSONObject jsonObject = new JSONObject(String.format("{ \"rating\": %f}", ratingBar.getRating()));
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonObject).toString());
                    Call<ResponseBody> response = addRatingAPI.addRating(courseId,memberId,body);

                    response.enqueue(new Callback<ResponseBody>()
                    {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
                        {
                            try
                            {
                                //get your response....
                                Log.d("Retro", "RetroFit2.0 : " + rawResponse.body().string());
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable throwable)
                        {

                        }
                    });
                }
                catch (JSONException e)
                {

                }
                Toast.makeText(AddRatingActivity.this,"Rating Finish!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
