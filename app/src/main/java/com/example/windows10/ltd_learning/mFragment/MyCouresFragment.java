package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mRecycler.CustomMyCourse;
import com.example.windows10.ltd_learning.mModel.MyCourse;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Windows10 on 10/11/2017.
 */

public class MyCouresFragment extends Fragment {
    private MyCourse myCourse;
    private List<String> course_name;
    private ArrayAdapter adapter;
    private ListView listView;
    private View rootView;
    private static int USER_ID;
    private static MyCourse course_all;
    private static final String URL_getMyCourse = "http://158.108.207.7:8090/elearning/course?studentId=";
    private  SharedPreferences sharedPreferences;
    private int user_id;
    private TextView mID;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private ImageView loadingImage;
    RecyclerView recyclerView;
    private HomeFragment.RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mycourse_frag_rv,container,false);

        loadingImage =  rootView.findViewById(R.id.imageView);

        Glide.with(getContext()).load(R.drawable.loading).into(loadingImage);

        Intent intent  = getActivity().getIntent();
        user_id = intent.getIntExtra("user_id",-1);

        SharedPreferences sp = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("user_ID",user_id);
//        editor.commit();
        USER_ID = sp.getInt("idMember",-1);
        Log.d("JSON","####TestShared"+USER_ID);
        Log.d("JSON","##IDfromMyCourse"+String.valueOf(USER_ID));
//        getInfomation(USER_ID);
        return rootView;
    }

    @Override
    public void onResume() {
        getMyCourse(URL_getMyCourse+String.valueOf(USER_ID));
        super.onResume();
    }

    public void getMyCourse(String url)
    {
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
                loadingImage.setVisibility(View.GONE);
                showData(jsonString);
            }


        }.execute(url);
    }

    public void showData(String jsonString)
    {
        if (!jsonString.equals(""))
        {
            Gson gson = new Gson();
            MyCourse data = gson.fromJson(jsonString,MyCourse.class);
            MyCourse.ResponseBean response = data.getResponse();

            if (response.isStatus())
            {
                List<MyCourse.CoursesBean> courses = data.getCourses();
//                List<MyCourse.CoursesBean> courses = getMyCourseFull();
                recyclerView = (RecyclerView) getView().findViewById(R.id.rv_mycourse);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CustomMyCourse(getContext(), courses));
                recyclerView.setHasFixedSize(true);
                recyclerView.setVisibility(View.INVISIBLE);
                recyclerViewReadyCallback = new HomeFragment.RecyclerViewReadyCallback() {
                    @Override
                    public void onLayoutReady() {
                        //
                        //here comes your code that will be executed after all items are laid down
                        //
                        loadingImage.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                };
                recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (recyclerViewReadyCallback != null) {
                            recyclerViewReadyCallback.onLayoutReady();
                        }
                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });

            }
            else
            {
                RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_mycourse);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(null);
            }
        }
        else
        {
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_mycourse);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(null);
        }
    }
}
