package com.example.windows10.ltd_learning.mFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.windows10.ltd_learning.mInterface.MyAPI;
import com.example.windows10.ltd_learning.mModel.CategoryAll;
import com.example.windows10.ltd_learning.mModel.Course;
import com.example.windows10.ltd_learning.mModel.CourseById;
import com.example.windows10.ltd_learning.mInterface.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mRecycler.CustomAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows10 on 2/11/2018.
 */

public class DetailCatFragment extends Fragment {
    private CourseById courseById_category;
    private List<Integer> id_in_courseCat;
    private ListView listView;
    private ArrayAdapter adapter;
    private List<CourseById> list_course;
    private static Course course_all;
    private static String URL_getCourseByID = "elearning/course?courseId=";
    private static CategoryAll course_by_cat;
    private static final String URL_getByCat_Id = "http://158.108.207.7:8090/elearning/category?id=";
    private int category_id;
    private static final String MyPREFERENCES = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.detail_cat_fragment_rv, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        listView = (ListView) rootView.findViewById(R.id.detail_cat_lv);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity().getApplicationContext(),CourseDetailForMyCourse.class);
//                intent.putExtra("course_name",listView.getItemAtPosition(i).toString());
//                startActivity(intent);
//            }
//        });
        category_id = sharedPreferences.getInt("id_category", -1);
        Log.d("JSON", "## From DetailCat " + category_id);
        getInfomation(category_id);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfomation(category_id);
            }
        });
        return rootView;
    }

    public void getInfomation(int id) {
        Log.d("JSON", "####TestURL" + URL_getByCat_Id + String.valueOf(category_id));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getByCat_Id + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                CategoryAll courseByCat = gson.fromJson(response, CategoryAll.class);
                    setCourseByCat(courseByCat);
                swipeRefreshLayout.setRefreshing(false);

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

    private void setCourseByCat(CategoryAll course) {
        course_by_cat = course;
        if(course.getCourseList() != null) {
            id_in_courseCat = new ArrayList<Integer>();
            for (int i = 0; i < course_by_cat.getCourseList().size(); i++) {
                id_in_courseCat.add(i, course_by_cat.getCourseList().get(i));
            }

        Log.d("JSON", "From set Course Check ID" + id_in_courseCat);
        String list_id = TextUtils.join(",", id_in_courseCat);
        Log.d("JSON", "From set Course Check ID" + list_id);
        getCourseFromId(MyAPI.BASE_URL_ELEARNNING+URL_getCourseByID + list_id);
        }
    }

    public void getCourseFromId(String url) {
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
                    Log.d("ClientProtocolException", e.getMessage());

                } catch (IOException e) {
                    Log.d("IOException", e.getMessage());
                }
                return result;
            }

            protected void onPostExecute(String jsonString) {
                showData(jsonString);
            }
        }.execute(url);
    }

    public void showData(String jsonString) {
        if (!jsonString.equals("")) {
            Gson gson = new Gson();
            Course data = gson.fromJson(jsonString, Course.class);

            Course.StatusBean response = data.getStatus();

            if (response.isStatus()) {
                List<Course.CoursesBean> courses = data.getCourses();
                RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_detail_cat);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CustomAdapter(getContext(), courses));
                recyclerView.setHasFixedSize(true);

            } else {
                RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_detail_cat);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(null);
            }
        } else {
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_detail_cat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(null);
        }
    }

}
