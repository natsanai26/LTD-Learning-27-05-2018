package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.windows10.ltd_learning.CategoryAll;
import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseByCat;
import com.example.windows10.ltd_learning.CourseNew;
import com.example.windows10.ltd_learning.CourseTop;
import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.SearchActivity;
import com.example.windows10.ltd_learning.SectionList;
import com.example.windows10.ltd_learning.ViewPagerAdapter;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.example.windows10.ltd_learning.mRecycler.Snap;
import com.example.windows10.ltd_learning.mRecycler.SnapAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Windows10 on 10/10/2017.
 */

public class HomeFragment extends Fragment {
    private List<Integer> idCourse_picTop,idCourse_picNew;
    private List<String> content_picTop,content_picNew;
    private ImageView imageView;
    private boolean isCheckAfLogin = false;
    private LinearLayout sliderDotpanel;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPager vp_image;
    private RecyclerView rv;
    private CardView cardView;
    private static CategoryAll[] cat_all;
    private static Course[] course_all;
    SharedPreferences sharedPreferences;
    private List<String> listOfURLPic = new ArrayList<String>();
    private static CourseTop courseTop;
    private static CourseNew courseNew;
    private static CourseByCat course_by_cat_1, course_by_cat_2, course_by_cat_3, course_by_cat_4;
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/app?id=";
    private static final String URL_getNewCourse = "http://158.108.207.7:8090/elearning/course?new=10";
    private static final String URL_getTopCourse = "http://158.108.207.7:8090/elearning/course?top=10";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        vp_image = (ViewPager) rootView.findViewById(R.id.viewPager_id);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getActivity());
        vp_image.setAdapter(viewPagerAdapter);

        sliderDotpanel = (LinearLayout) rootView.findViewById(R.id.sliderDot);
        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this.getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            sliderDotpanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

        vp_image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

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
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setNestedScrollingEnabled(false);
        return rootView;
    }

    private void getInfoNewCourse(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getNewCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("##JSON", response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseNew course = gson.fromJson(response, CourseNew.class);
                Log.d("##JSON", "Fromm NewCourse"+course.getResponse().getMessage() + " " + course.getCourses().get(0).getName() + " " + course.getCourses().size());
                setNewCourse(course);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(stringRequest);

    }
    private void getInfoTopCourse() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getTopCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("##JSON", response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseTop course = gson.fromJson(response, CourseTop.class);
                Log.d("##JSON", course.getResponse().getMessage() + " " + course.getCourses().get(0).getName() + " " + course.getCourses().size());
                setTopCourse(course);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(stringRequest);

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

    private void setupAdapterTop() {
        List<Course> course_tops = getTopCourse();
        List<Course> course_news = getNewCourse();
        SnapAdapter snapAdapter = new SnapAdapter();
        snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "New Course ( "+course_news.size()+" )", course_news));
        snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Top Course ( "+course_tops.size()+" ) ", course_tops));

        rv.setAdapter(snapAdapter);
    }


    private void setNewCourse(CourseNew course){
        courseNew = course;
        setURLPictureNewCourse();
        setupAdapterTop();
    }
    private void setTopCourse(CourseTop course) {
        courseTop = course;
        setURLPictureTopCourse();
    }

    private void setListOfUrLPic(String url){
        listOfURLPic.add(url);
    }
    private List<String> getListOfUrlPic(){
        return listOfURLPic;
    }

    private void setURLPictureNewCourse(){
        content_picNew = new ArrayList<String>();
        idCourse_picNew = new ArrayList<Integer>();
        List<SectionList> sectionList = new ArrayList<>();
        for (int i = 0; i < courseNew.getCourses().size(); i++) {
            sectionList = courseNew.getCourses().get(i).getSectionList();
            if (sectionList.size() != 0 && sectionList.get(0)!=null && sectionList.get(0).getRank() == 0) {
                Log.d("JSON","content --> "+sectionList.get(0).getContent()+" name course "+ courseNew.getCourses().get(i).getName());
                content_picNew.add(sectionList.get(0).getContent());
                idCourse_picNew.add(courseNew.getCourses().get(i).getId());
//                getURLFromContentPicture(sectionList.get(0).getContent());
            }
        }

    }

    private void setURLPictureTopCourse(){
        content_picTop = new ArrayList<String>();
        idCourse_picTop = new ArrayList<Integer>();
        List<SectionList> sectionList = new ArrayList<>();
        for (int i = 0; i < courseTop.getCourses().size(); i++) {
            sectionList = courseTop.getCourses().get(i).getSectionList();
            if (sectionList.size() != 0 && sectionList.get(0)!=null && sectionList.get(0).getRank() == 0) {
                Log.d("JSON","content --> "+sectionList.get(0).getContent()+" name course "+ courseTop.getCourses().get(i).getName());
                content_picTop.add(sectionList.get(0).getContent());
                idCourse_picTop.add(courseTop.getCourses().get(i).getId());
//                getURLFromContentPicture(sectionList.get(0).getContent());
            }
        }

//        for(int i =0; i<content_pic.size();i++){
//            Log.d("JSON","content pic -------> "+content_pic.get(i)+" FROM ID "+idCourse_pic.get(i));
//        }

    }

    private List<Course> getNewCourse() {
        List<Course> courses = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < courseNew.getCourses().size()&& j < idCourse_picNew.size(); i++) { //&& j < idCourse_picNew.size()

//            courses.add(new Course.CoursesBean(courseNew.getCourses().get(i).getName(), courseNew.getCourses().get(i).getId()
//                    ,courseNew.getCourses().get(i).getRating(),"",courseNew.getCourses().get(i).getVoter()));

            if(idCourse_picNew.get(j) == courseNew.getCourses().get(i).getId()){
                courses.add(new Course.CoursesBean(courseNew.getCourses().get(i).getName(), courseNew.getCourses().get(i).getId()
                        ,courseNew.getCourses().get(i).getRating(),content_picNew.get(j),courseNew.getCourses().get(i).getVoter()));
                Log.d("JSON","in loop NewCourse -------> "+content_picNew.get(j));
                j++;

            }
            else {
                courses.add(new Course.CoursesBean(courseNew.getCourses().get(i).getName(), courseNew.getCourses().get(i).getId()
                        ,courseNew.getCourses().get(i).getRating(),"",courseNew.getCourses().get(i).getVoter()));
            }
        }
        return courses;
    }

    private List<Course> getTopCourse() {
        List<Course> courses = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < courseTop.getCourses().size(); i++) {
//            courses.add(new Course.CoursesBean(courseTop.getCourses().get(i).getName(), courseTop.getCourses().get(i).getId()
//                    ,courseTop.getCourses().get(i).getRating(),"",courseTop.getCourses().get(i).getVoter()));
                if(idCourse_picTop.get(j) == courseTop.getCourses().get(i).getId()){
                    courses.add(new Course.CoursesBean(courseTop.getCourses().get(i).getName(), courseTop.getCourses().get(i).getId()
                            ,courseTop.getCourses().get(i).getRating(),content_picTop.get(j),courseTop.getCourses().get(i).getVoter()));
                    j++;
                    if(j == idCourse_picTop.size()){
                        break;
                    }
                }
                else {
                    courses.add(new Course.CoursesBean(courseTop.getCourses().get(i).getName(), courseTop.getCourses().get(i).getId()
                            ,courseTop.getCourses().get(i).getRating(),"",courseTop.getCourses().get(i).getVoter()));
                }
        }
        return courses;
    }

    private void getURLFromContentPicture(String content) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+response);
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