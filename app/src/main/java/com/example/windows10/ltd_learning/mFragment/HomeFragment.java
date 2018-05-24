package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.windows10.ltd_learning.mModel.CategoryAll;
import com.example.windows10.ltd_learning.mModel.Course;
import com.example.windows10.ltd_learning.mModel.CourseByCat;
import com.example.windows10.ltd_learning.mModel.CourseNew;
import com.example.windows10.ltd_learning.mModel.CourseTop;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.MyAPI;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mModel.SectionList;
import com.example.windows10.ltd_learning.mRecycler.CustomAdapter;
import com.example.windows10.ltd_learning.mRecycler.HomeAdapter;
import com.example.windows10.ltd_learning.mRecycler.Snap;
import com.example.windows10.ltd_learning.mRecycler.SnapAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Windows10 on 10/10/2017.
 */

public class HomeFragment extends Fragment {
    private List<Integer> idCourse_picTop, idCourse_picNew;
    private List<String> content_picTop, content_picNew;
    private ImageView imageView;
    private boolean isCheckAfLogin = false;
    private LinearLayout sliderDotpanel;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPager vp_image;
    private RecyclerView rv;
    private RecyclerView rv2;
    private CardView cardView;
    private static CategoryAll[] cat_all;
    private static Course[] course_all;
    SharedPreferences sharedPreferences;
    private List<String> listOfURLPic = new ArrayList<String>();
    private static CourseTop courseTop;
    private static CourseNew courseNew;
    private static CourseByCat course_by_cat_1, course_by_cat_2, course_by_cat_3, course_by_cat_4;
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/app?id=";
    private ElearningAPI elearningAPI;
    private TextView cat1;
    private TextView cat2;
    private ImageView loadingImage;
    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    private  boolean b1=false,b2=false;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        elearningAPI = MyAPI.getAPI();
        loadingImage = rootView.findViewById(R.id.imageView);
        cat1 = (TextView) rootView.findViewById(R.id.cat1);
        cat2 = (TextView) rootView.findViewById(R.id.cat2);
        //Glide.with(getContext()).load(R.drawable.spinner).into(loadingImage);




        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "not found");
        String password = sharedPreferences.getString("password", "not found");


        if (!username.equals("not found") && !password.equals("not found")) {
//            MainActivity.bottomNavigationItem.enableItemAtPosition(2);
//            MainActivity.bottomNavigationItem.getItem(2).setDrawable(R.drawable.ic_school);
//            MainActivity.bottomNavigationItem.getItem(2).setTitle("MyCourse");
        } else {
//            MainActivity.bottomNavigationItem.disableItemAtPosition(2);

        }
//        getInfomation();
        getInfoTopCourse();
        getInfoNewCourse();


        rv = (RecyclerView) rootView.findViewById(R.id.home_RV);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rv.setNestedScrollingEnabled(false);

        rv2 = (RecyclerView) rootView.findViewById(R.id.home_RV_2);
        rv2.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));



        return rootView;
    }




    private void getInfoNewCourse() {

        Call<ResponseBody> responseBody = elearningAPI.getNewCourse(10);
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        //CourseNew course = gson.fromJson(result, CourseNew.class);
                        Course course = gson.fromJson(result,Course.class);
                        HomeAdapter homeAdapter = new HomeAdapter(getContext(),course.getCourses());
                        rv.setAdapter(homeAdapter);
                        //setNewCourse(course);
                        cat1.setText(String.format("New courses (%d)",course.getCourses().size()));

                        b1=true;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    private void getInfoTopCourse() {



        Call<ResponseBody> responseBody = elearningAPI.getTopCourse(10);
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        //CourseTop course = gson.fromJson(result, CourseTop.class);
                        Course course = gson.fromJson(result, Course.class);
                        //setTopCourse(course);
                        HomeAdapter homeAdapter = new HomeAdapter(getContext(),course.getCourses());
                        rv2.setAdapter(homeAdapter);



                        cat2.setText(String.format("Top courses (%d)",course.getCourses().size()));
                        b2=true;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    public class MyTimerTask extends TimerTask {
        final boolean keeprunning = true;

        @Override
        public void run() {

            if (getActivity() == null) {
                return;
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (vp_image.getCurrentItem() == 0) {
                        vp_image.setCurrentItem(1);
                    } else if (vp_image.getCurrentItem() == 1) {
                        vp_image.setCurrentItem(2);
                    } else {
                        vp_image.setCurrentItem(0);
                    }
                }
            });

        }
    }

    SnapAdapter snapAdapter;

    /*private void setupAdapterTop() {

        List<Course> course_tops = getTopCourse();
        List<Course> course_news = getNewCourse();


        snapAdapter = new SnapAdapter();
//        snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "New Courses (" + course_news.size() + ")", course_news));
//        snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Top Courses (" + course_tops.size() + ") ", course_tops));
        //snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "New Courses (" + course_news.size() + ")", courseNew));
        //snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Top Courses (" + course_tops.size() + ") ", courseTop));


        rv.setAdapter(snapAdapter);
        rv.setVisibility(View.INVISIBLE);
        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {
                //
                //here comes your code that will be executed after all items are laid down
                //
                loadingImage.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        };
        rv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback != null) {
                    recyclerViewReadyCallback.onLayoutReady();
                }
                rv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

        });

    }*/


    private void setNewCourse(CourseNew course) {
        courseNew = course;
        setURLPictureNewCourse();
        //setupAdapterTop();
    }

    private void setTopCourse(CourseTop course) {
        courseTop = course;
        setURLPictureTopCourse();
    }

    private void setListOfUrLPic(String url) {
        listOfURLPic.add(url);
    }

    private List<String> getListOfUrlPic() {
        return listOfURLPic;
    }

    private void setURLPictureNewCourse() {
        content_picNew = new ArrayList<String>();
        idCourse_picNew = new ArrayList<Integer>();
        List<SectionList> sectionList = new ArrayList<>();
        for (int i = 0; i < courseNew.getCourses().size(); i++) {
            sectionList = courseNew.getCourses().get(i).getSectionList();
            if (sectionList.size() != 0 && sectionList.get(0) != null && sectionList.get(0).getRank() == 0) {
                Log.d("JSON", "content --> " + sectionList.get(0).getContent() + " name course " + courseNew.getCourses().get(i).getName());
                content_picNew.add(sectionList.get(0).getContent());
                idCourse_picNew.add(courseNew.getCourses().get(i).getId());
//                getURLFromContentPicture(sectionList.get(0).getContent());
            }
        }

    }

    private void setURLPictureTopCourse() {
        content_picTop = new ArrayList<String>();
        idCourse_picTop = new ArrayList<Integer>();
        List<SectionList> sectionList = new ArrayList<>();
        for (int i = 0; i < courseTop.getCourses().size(); i++) {
            sectionList = courseTop.getCourses().get(i).getSectionList();
            if (sectionList.size() != 0 && sectionList.get(0) != null && sectionList.get(0).getRank() == 0) {
                Log.d("JSON", "content --> " + sectionList.get(0).getContent() + " name course " + courseTop.getCourses().get(i).getName());
                content_picTop.add(sectionList.get(0).getContent());
                idCourse_picTop.add(courseTop.getCourses().get(i).getId());
//                getURLFromContentPicture(sectionList.get(0).getContent());
            }
        }

    }

    private List<Course> getNewCourse() {
        List<Course> courses = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < courseNew.getCourses().size() && j < idCourse_picNew.size(); i++) { //&& j < idCourse_picNew.size()


            if (idCourse_picNew.get(j) == courseNew.getCourses().get(i).getId()) {
                courses.add(new Course.CoursesBean(courseNew.getCourses().get(i).getName(), courseNew.getCourses().get(i).getId()
                        , courseNew.getCourses().get(i).getRating(), content_picNew.get(j), courseNew.getCourses().get(i).getVoter()
                        , courseNew.getCourses().get(i).getTeacher().getName(), courseNew.getCourses().get(i).getTeacher().getSurname()
                        , (String) courseNew.getCourses().get(i).getTeacher().getPhotoUrl()));
                Log.d("JSON", "in loop NewCourse -------> " + content_picNew.get(j) + " T " + courseNew.getCourses().get(i).getTeacher().getPhotoUrl());
                j++;

            } else {
                courses.add(new Course.CoursesBean(courseNew.getCourses().get(i).getName(), courseNew.getCourses().get(i).getId()
                        , courseNew.getCourses().get(i).getRating(), "", courseNew.getCourses().get(i).getVoter()));
            }
        }
        return courses;
    }

    private List<Course> getTopCourse() {
        List<Course> courses = new ArrayList<>();
        int j = 0;
        try {
            for (int i = 0; i < courseTop.getCourses().size() && j < idCourse_picTop.size(); i++) {
                if (idCourse_picTop.get(j) == courseTop.getCourses().get(i).getId()) {
                    courses.add(new Course.CoursesBean(courseTop.getCourses().get(i).getName(), courseTop.getCourses().get(i).getId()
                            , courseTop.getCourses().get(i).getRating(), content_picTop.get(j), courseTop.getCourses().get(i).getVoter()
                            , courseTop.getCourses().get(i).getTeacher().getName(), courseTop.getCourses().get(i).getTeacher().getSurname()
                            , (String) courseNew.getCourses().get(i).getTeacher().getPhotoUrl()));
                    j++;
                } else {
                    courses.add(new Course.CoursesBean(courseTop.getCourses().get(i).getName(), courseTop.getCourses().get(i).getId()
                            , courseTop.getCourses().get(i).getRating(), "", courseTop.getCourses().get(i).getVoter()));
                }
            }
        }
        catch (NullPointerException e)
        {
            getActivity().finish();
            getActivity().startActivity(getActivity().getIntent());
        }

        return courses;
    }

    private void getURLFromContentPicture(String content) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON", "url --> " + response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", "key999");
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToReauestQue(stringRequest);
    }

}