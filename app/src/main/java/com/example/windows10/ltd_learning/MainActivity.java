package com.example.windows10.ltd_learning;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.windows10.ltd_learning.mFragment.HomeFragment;
import com.example.windows10.ltd_learning.mFragment.MyCouresFragment;
import com.example.windows10.ltd_learning.mFragment.ProfileFragment;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Course[] course_all;
    private static final String URL = "http://158.108.207.7:8090/elearning/course";
    private MaterialSearchView searchView;
    private ListView listView;
    public static AHBottomNavigation bottomNavigationItem;
    private static String[] course_new;
    private RecyclerView mRecyclerView;
    private String[] course_S = {"Course ONE","Course TWO","Course TREE","Course FOUR","Course FIVE","Course SIX","Course SEVEN","Course EIGHT"};
    private ArrayAdapter<String> adapter;
//    List<Course> courses = getCourse();
    private Gson gson = new Gson();
    private Adapter mAdapter;
    private static Course[] courseResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent  = getIntent();
        course_new = intent.getStringArrayExtra("key");
        if(course_new != null){
            Log.d("JSON","##"+course_new[20]);
            finish();
        }
//        getInfomation();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("LTD Learning");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        listView = (ListView)findViewById(R.id.listView_id);
        bottomNavigationItem = (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigationItem.setBehaviorTranslationEnabled(false);
        bottomNavigationItem.setAccentColor(Color.parseColor("#ffd2a5"));
        bottomNavigationItem.setInactiveColor(Color.parseColor("#747474"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        AHBottomNavigation.OnTabSelectedListener onTabSelectedListener1 = new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch(position)
                {

                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_id,homeFragment).commit();

                        break;
                    case 1:
                        MyCouresFragment myCouresFragment  = new MyCouresFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_id,myCouresFragment).commit();
                        break;
                    case 2:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_id,profileFragment).commit();
                        break;
                }

                return true;
            }
        };
        bottomNavigationItem.setOnTabSelectedListener(onTabSelectedListener1);




//       searchView = (MaterialSearchView)findViewById(R.id.search_view);
        this.createNavItems();
    }

    private void getInfomation(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

//                List<Course> courses = Arrays.asList(gson.fromJson(response,Course[].class));
                java.lang.reflect.Type collectionType = new TypeToken<Collection<Course>>() {}.getType();
                Collection<Course> enums = gson.fromJson(response,collectionType);
                Course[] courseResult = enums.toArray(new Course[enums.size()]);
                Log.d("JSON","##"+courseResult[2].getName());

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(this).addToReauestQue(stringRequest);
    }

    private void createNavItems(){
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem("Home",R.drawable.ic_home);
        AHBottomNavigationItem myCourseItem = new AHBottomNavigationItem("",R.drawable.ic_null);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem("Profile",R.drawable.ic_account);
        bottomNavigationItem.addItem(homeItem);
        bottomNavigationItem.addItem(myCourseItem);
        bottomNavigationItem.addItem(profileItem);



        bottomNavigationItem.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        bottomNavigationItem.setCurrentItem(0);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search){
            Toast.makeText(this,"Hello Search",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,SearchActivity.class);
            intent.putExtra("key_name",course_new);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
