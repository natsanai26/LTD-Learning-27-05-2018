package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseByCat;
import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.SearchActivity;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.example.windows10.ltd_learning.mRecycler.Snap;
import com.example.windows10.ltd_learning.mRecycler.SnapAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Windows10 on 10/10/2017.
 */

public class HomeFragment extends Fragment {
    private RecyclerView rv;
    private CardView cardView;
    private static Course[] course_all;
    SharedPreferences sharedPreferences;
    private static CourseByCat course_by_cat_1,course_by_cat_2,course_by_cat_3,course_by_cat_4;
    private static final String URL_getAllCourse = "http://158.108.207.7:8090/elearning/course";
    private static final String URL_getByCat_1 = "http://158.108.207.7:8090/elearning/course?idCategory=19";
    private static final String URL_getByCat_2 = "http://158.108.207.7:8090/elearning/course?idCategory=38";
    private static final String URL_getByCat_3 = "http://158.108.207.7:8090/elearning/course?idCategory=36";
    private static final String URL_getByCat_4 = "http://158.108.207.7:8090/elearning/course?idCategory=37";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment,container,false);

        sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","not found");
        String password = sharedPreferences.getString("password","not found");


        if (!username.equals("not found")&&!password.equals("not found"))
        {
            MainActivity.bottomNavigationItem.enableItemAtPosition(1);
            MainActivity.bottomNavigationItem.getItem(1).setDrawable(R.drawable.ic_school);
            MainActivity.bottomNavigationItem.getItem(1).setTitle("MyCourse");
        }
        else
        {
            MainActivity.bottomNavigationItem.disableItemAtPosition(1);
        }

        getInfomation();

        rv = (RecyclerView) rootView.findViewById(R.id.home_RV);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setHasFixedSize(true);
        return rootView;
    }
    private void setupAdapter(){
        List<Course> courses_cat1 = getCourse_cat1();
        List<Course> courses_cat2 = getCourse_cat2();
        List<Course> courses_cat3 = getCourse_cat3();
        List<Course> courses_cat4 = getCourse_cat4();
        SnapAdapter snapAdapter = new SnapAdapter();
        snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Category 1", courses_cat1));
        snapAdapter.addSnap(new Snap(Gravity.START, "Category 2", courses_cat2));
        snapAdapter.addSnap(new Snap(Gravity.END, "Category 3", courses_cat3));
        snapAdapter.addSnap(new Snap(Gravity.CENTER, "Category 4", courses_cat4));

        putExtraName();
        rv.setAdapter(snapAdapter);
    }
    private void putExtraName(){
        String[] courseName = new String[course_all.length];
        Intent intent = new Intent(getContext(), MainActivity.class);
        for (int i = 0;i<course_all.length;i++){
            courseName[i] = course_all[i].getName();
        }
        intent.putExtra("key",courseName);
        Log.d("JSON","##"+courseName[20]);
        startActivity(intent);

    }
    private void getInfomation(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getAllCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

//                List<Course> courses = Arrays.asList(gson.fromJson(response,Course[].class));
                java.lang.reflect.Type collectionType = new TypeToken<Collection<Course>>() {}.getType();
                Collection<Course> enums = gson.fromJson(response,collectionType);
                Course[] courseResult = enums.toArray(new Course[enums.size()]);

                setAllCourse(courseResult);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(stringRequest);
        //----------------------------------------------------------------------
        StringRequest SR_CourseByCat1 = new StringRequest(Request.Method.GET, URL_getByCat_1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseByCat courseByCat = gson.fromJson(response,CourseByCat.class);


                setCourseByCat1(courseByCat);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(SR_CourseByCat1);
        //----------------------------------------------------------------------
        StringRequest SR_CourseByCat2 = new StringRequest(Request.Method.GET, URL_getByCat_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseByCat courseByCat = gson.fromJson(response,CourseByCat.class);


                setCourseByCat2(courseByCat);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(SR_CourseByCat2);
        //----------------------------------------------------------------------
        StringRequest SR_CourseByCat3 = new StringRequest(Request.Method.GET, URL_getByCat_3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseByCat courseByCat = gson.fromJson(response,CourseByCat.class);


                setCourseByCat3(courseByCat);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(SR_CourseByCat3);
        //----------------------------------------------------------------------
        StringRequest SR_CourseByCat4 = new StringRequest(Request.Method.GET, URL_getByCat_4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CourseByCat courseByCat = gson.fromJson(response,CourseByCat.class);


                setCourseByCat4(courseByCat);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(getContext()).addToReauestQue(SR_CourseByCat4);

    }

    private void setAllCourse(Course[] courses){

        course_all = courses;

    }
    private void setCourseByCat1(CourseByCat course){
        course_by_cat_1 = course;
    }
    private void setCourseByCat2(CourseByCat course){
        course_by_cat_2 = course;
    }
    private void setCourseByCat3(CourseByCat course){
        course_by_cat_3 = course;
    }
    private void setCourseByCat4(CourseByCat course){
        course_by_cat_4 = course;
        setupAdapter();
    }

    private List<Course> getCourse_cat1(){
        List<Course> courses = new ArrayList<>();
        Log.d("JSON","##"+course_by_cat_1.getCourseList().get(0)+"--"+course_by_cat_1.getCourseList().size());
        int j = 0;
        for(int i = 0 ;i<course_all.length ;i++){
            if(j<course_by_cat_1.getCourseList().size()) {
                if (course_by_cat_1.getCourseList().get(j) == course_all[i].getId()) {
                    courses.add(new Course(course_all[i].getName()));
                    j++;
                }
            }
        }
//        courses.add(new Course("Course Home 2"));
//        courses.add(new Course("Course Home 3"));
//        courses.add(new Course("Course Home 4"));
//        courses.add(new Course("Course Home 5"));
//        courses.add(new Course("Course Home 6"));
//        courses.add(new Course("Course Home 7"));
//        courses.add(new Course("Course Home 8"));
//        courses.add(new Course("Course Home 9"));
//        courses.add(new Course("Course Home 10"));
        return courses;
    }
    private List<Course> getCourse_cat2(){
        List<Course> courses = new ArrayList<>();
        Log.d("JSON", "##" + course_by_cat_2.getCourseList().get(0) + "--" + course_by_cat_2.getCourseList().size());
        int j = 0;
        for (int i = 0; i < course_all.length; i++) {
            if (j < course_by_cat_2.getCourseList().size()) {
                if (course_by_cat_2.getCourseList().get(j) == course_all[i].getId()) {
                    courses.add(new Course(course_all[i].getName()));
                    j++;
                }
            }
        }
        return courses;
    }
    private List<Course> getCourse_cat3(){
        List<Course> courses = new ArrayList<>();
        Log.d("JSON", "##" + course_by_cat_3.getCourseList().get(0) + "--" + course_by_cat_3.getCourseList().size());
        int j = 0;
        for (int i = 0; i < course_all.length; i++) {
            if (j < course_by_cat_3.getCourseList().size()) {
                if (course_by_cat_3.getCourseList().get(j) == course_all[i].getId()) {
                    courses.add(new Course(course_all[i].getName()));
                    j++;
                }
            }
        }
        return courses;
    }
    private List<Course> getCourse_cat4(){
        List<Course> courses = new ArrayList<>();
        Log.d("JSON", "##" + course_by_cat_4.getCourseList().get(0) + "--" + course_by_cat_4.getCourseList().size());
        int j = 0;
        for (int i = 0; i < course_all.length; i++) {
            if (j < course_by_cat_4.getCourseList().size()) {
                if (course_by_cat_4.getCourseList().get(j) == course_all[i].getId()) {
                    courses.add(new Course(course_all[i].getName()));
                    j++;
                }
            }
        }
        return courses;
    }

}
