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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseDetail;
import com.example.windows10.ltd_learning.CourseDetailForMyCourse;
import com.example.windows10.ltd_learning.CourseTop;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.example.windows10.ltd_learning.mRecycler.CustomAdapter;
import com.example.windows10.ltd_learning.mRecycler.CustomMyCourse;
import com.example.windows10.ltd_learning.mRecycler.MyCourse;
import com.example.windows10.ltd_learning.mRecycler.Snap;
import com.example.windows10.ltd_learning.mRecycler.SnapAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
import java.util.Collection;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mycourse_frag_rv,container,false);
//        listView = (ListView)rootView.findViewById(R.id.mycourse_lv);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity().getApplicationContext(),CourseDetailForMyCourse.class);
//                intent.putExtra("course_name",listView.getItemAtPosition(i).toString());
//                startActivity(intent);
//            }
//        });


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
        getMyCourse(URL_getMyCourse+String.valueOf(USER_ID));
        return rootView;
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
                RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rv_mycourse);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CustomMyCourse(getContext(), courses));
                recyclerView.setHasFixedSize(true);

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
//    public void getInfomation(int id){
//        Log.d("JSON","####TestURL"+URL_getMyCourse+String.valueOf(USER_ID));
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getMyCourse+id, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                Log.d("##JSON",response);
//                GsonBuilder builder = new GsonBuilder();
//                Gson gson = builder.create();
//                MyCourse courseResult = gson.fromJson(response,MyCourse.class);
//                Log.d("##JSON",courseResult.getResponse().getMessage()+" "+courseResult.getCourses().get(0).getName()+" "+courseResult.getCourses().size());
//                setAllCourse(courseResult);
////                GsonBuilder builder = new GsonBuilder();
////                Gson gson = builder.create();
////
//////                List<Course> courses = Arrays.asList(gson.fromJson(response,Course[].class));
////                java.lang.reflect.Type collectionType = new TypeToken<Collection<MyCourse>>() {}.getType();
////                Collection<MyCourse> enums = gson.fromJson(response,collectionType);
////                MyCourse.CoursesBean[] courseResult = enums.toArray(new MyCourse.CoursesBean[enums.size()]);
////                setAllCourse(courseResult);
//
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("JSON","Error JSON");
//                    }
//                });
//        MySingleton.getInstance(getContext()).addToReauestQue(stringRequest);
//
//    }
//
//    private void setAllCourse(MyCourse courses){
//
//        course_all = courses;
//        Log.d("JSON","##FromGetInMyCourse-->"+course_all.getCourses().get(0).getName());
//
//        addCourse();
//
//
//    }
//
//    public void addCourse(){
//        course_name = new ArrayList<String>();
//        Log.d("JSON","##FromAddCourse-->"+course_all.getCourses().get(0).getName());
//        if(course_all != null) {
//
//            for (int i = 0; i < course_all.getCourses().size(); i++) {
//                course_name.add(course_all.getCourses().get(i).getName());
//            }
//        }
//        else {
//            course_name.add("Test my Course1");
//            course_name.add("Test my Course2");
//            course_name.add("Test my Course3");
//        }
//
//
//        adapter = new ArrayAdapter(getActivity().getApplicationContext(),R.layout.list_item_mycourse,course_name);
//        listView.setAdapter(adapter);
//    }

}
