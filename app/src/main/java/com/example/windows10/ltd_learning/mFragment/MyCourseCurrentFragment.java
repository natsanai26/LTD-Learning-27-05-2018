package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.windows10.ltd_learning.mInterface.MyAPI;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.mModel.MyCourse;
import com.example.windows10.ltd_learning.mRecycler.CustomMyCourse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by natsanai on 5/25/2018.
 */

public class MyCourseCurrentFragment extends Fragment {
    private MyCourse myCourse;
    private List<String> course_name;
    private ArrayAdapter adapter;
    private ListView listView;
    private View rootView;
    private static int USER_ID;
    private static MyCourse course_all;
    private SharedPreferences sharedPreferences;
    private int user_id;
    private TextView mID;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private ImageView loadingImage;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    private ElearningAPI elearningAPI;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mycourse_current_fragment,container,false);
        elearningAPI = MyAPI.getAPI();
        loadingImage =  rootView.findViewById(R.id.imageView);
        recyclerView = rootView.findViewById(R.id.rv_mycourse);
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
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyCourse();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        getMyCourse();
        super.onResume();
    }

    public void getMyCourse()
    {

        Call<ResponseBody> responseBody = elearningAPI.getCourseByStudentId(USER_ID);
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> rawResponse) {
                try {
                    String jsonString = rawResponse.body().string();
                    Log.d("js","+++"+jsonString);
                    if (!jsonString.equals(""))
                    {
                        Gson gson = new Gson();
                        MyCourse data = gson.fromJson(jsonString,MyCourse.class);
                        MyCourse.ResponseBean response = data.getResponse();

                        if (response.isStatus())
                        {
                            List<MyCourse.CoursesBean> courses = data.getCourses();
//                List<MyCourse.CoursesBean> courses = getMyCourseFull();
                            List<MyCourse.CoursesBean> finishedCourse = new ArrayList<MyCourse.CoursesBean>();

                            for (MyCourse.CoursesBean coursesBean:courses)
                            {
                                if (coursesBean.getProgress()!=null) {
                                    if (coursesBean.getProgress().getPercent() < 100) {
                                        finishedCourse.add(coursesBean);
                                    }
                                }
                                else {
                                    finishedCourse.add(coursesBean);
                                }
                            }

                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(new CustomMyCourse(getContext(), finishedCourse));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setVisibility(View.INVISIBLE);
                            recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
