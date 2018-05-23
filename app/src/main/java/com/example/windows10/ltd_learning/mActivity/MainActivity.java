package com.example.windows10.ltd_learning.mActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.windows10.ltd_learning.MyAPI;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;
import com.example.windows10.ltd_learning.mModel.CategoryAll;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mFragment.DetailCatFragment;
import com.example.windows10.ltd_learning.mFragment.HomeFragment;
import com.example.windows10.ltd_learning.mFragment.MyCouresFragment;
import com.example.windows10.ltd_learning.mFragment.ProfileFragment;
import com.example.windows10.ltd_learning.mFragment.SearchFragment;
import com.example.windows10.ltd_learning.mModel.Course;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    public static AHBottomNavigation.OnTabSelectedListener onTabSelectedListener2;
    private SharedPreferences sharedPreferences;
    private ActionBar ab;
    private ActionBarDrawerToggle mDrawerToggle;
    private static final  String URL_getAllCat = "http://158.108.207.7:8090/elearning/category";
    private static ArrayList<CategoryAll> cat_all;
    private List<String> category_name;
    private ArrayAdapter adapter_array;
    private String[] mDrawerTitle = {"Cover", "Guitar", "Bass", "Drum"};
    private static final String MyPREFERENCES = "MyPrefs";
    private DrawerLayout myDrawer;
    private ActionBarDrawerToggle myTogle;
    private boolean isCheckAfLogin = false,isCheckForEnroll =false;
    private Toolbar toolbar;
    private static boolean CheckOnSearch = false;
    private static Course[] course_all;
    private static final String URL = "http://158.108.207.7:8090/elearning/course";
    private MaterialSearchView searchView;
    private ListView mListView;
    public static AHBottomNavigation bottomNavigationItem;
    private int user_id;
    private static String[] course_new;
    private RecyclerView mRecyclerView;
    private String[] course_S = {"Course ONE","Course TWO","Course TREE","Course FOUR","Course FIVE","Course SIX","Course SEVEN","Course EIGHT"};
    private ArrayAdapter<String> adapter;
//    List<Course> courses = getCourse();
    private Gson gson = new Gson();
    private Adapter mAdapter;
    private static Course[] courseResult;
    private ElearningAPI elearningAPI;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elearningAPI = MyAPI.getAPI();
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getInfoCategory();
        Intent intent  = getIntent();
        course_new = intent.getStringArrayExtra("key");
        user_id = intent.getIntExtra("user_id",0);
        if(course_new != null ){
            Log.d("JSON","##"+course_new[20]);
            finish();
        }
        myDrawer = (DrawerLayout) findViewById(R.id.drawer_id);
        mListView = (ListView) findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.lit_item_category, mDrawerTitle);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (cat_all.get(i).getCourseList()!=null) {
                    DetailCatFragment fragment = new DetailCatFragment();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("id_category", cat_all.get(i).getId());
                    editor.commit();

                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, fragment).addToBackStack(null).commit();
                    toolbar.setTitle(cat_all.get(i).getCategoryName());
                    Log.d("JSON", "## From item click " + cat_all.get(i).getId() + "..." + i);
                    myDrawer.closeDrawers();
                }
            }
        });

//        getInfomation();

//        myDrawer = (DrawerLayout) findViewById(R.id.drawer_id);
//        myTogle = new ActionBarDrawerToggle(this,myDrawer,R.string.open,R.string.close);
//        myDrawer.addDrawerListener(myTogle);
//        myTogle.syncState();

//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_list);
//        configureNavigationDrawer();

        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        bottomNavigationItem = (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigationItem.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigationItem.setBehaviorTranslationEnabled(false);
        bottomNavigationItem.setAccentColor(Color.parseColor("#343434"));
        bottomNavigationItem.setInactiveColor(Color.parseColor("#FFFFFF"));
        bottomNavigationItem.setTranslucentNavigationEnabled(true);

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setHasFixedSize(true);
        AHBottomNavigation.OnTabSelectedListener onTabSelectedListener1 = new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                SharedPreferences sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                isCheckAfLogin = sp.getBoolean("checkLogin",false);
                Log.d("JSON","####TesIsCheck"+isCheckAfLogin);
                if(!isCheckAfLogin) {
                    switch (position) {

                        case 0:
                            HomeFragment homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, homeFragment).commit();
                            toolbar.setTitle("Home");
                            break;
                        case 1:
                            SearchFragment searchFragment = new SearchFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, searchFragment).commit();

                            CheckOnSearch = true;
                            toolbar.setTitle("Search");
                            break;
//                        case 2:
//                            MyCouresFragment myCouresFragment = new MyCouresFragment();
//                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, myCouresFragment).commit();
//                            toolbar.setTitle("MyCourses");
//                            break;
                        case 2:
                            ProfileFragment profileFragment = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, profileFragment).commit();
                            toolbar.setTitle("Login");
                            break;
                    }
                }
                else{
                    switch (position) {

                        case 0:
                            HomeFragment homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, homeFragment).commit();
                            toolbar.setTitle("Home");
                            break;
                        case 1:
                            SearchFragment searchFragment = new SearchFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, searchFragment).commit();
                            CheckOnSearch = true;
                            toolbar.setTitle("Search");
                            break;
                        case 2:
                            MyCouresFragment myCouresFragment = new MyCouresFragment();

                                getSupportFragmentManager().beginTransaction().replace(R.id.content_id, myCouresFragment).commit();
                            toolbar.setTitle("MyCourses");
                            break;
                        case 3:
                            ProfileFragment profileFragment = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_id, profileFragment).commit();
                            toolbar.setTitle("Profile");
                            break;
                    }

                }

                return true;
            }
        };
        bottomNavigationItem.setOnTabSelectedListener(onTabSelectedListener1);
        onTabSelectedListener2 = onTabSelectedListener1;




//       searchView = (MaterialSearchView)findViewById(R.id.search_view);
        this.createNavItems();
    }

    private void getInfoCategory(){
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getAllCat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                java.lang.reflect.Type collectionType = new TypeToken<Collection<CategoryAll>>() {}.getType();
                Collection<CategoryAll> enums = gson.fromJson(response,collectionType);
                CategoryAll[] categoryAllsResult = enums.toArray(new CategoryAll[enums.size()]);

                setAllCat(categoryAllsResult);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON","Error JSON");
                    }
                });
        MySingleton.getInstance(this).addToReauestQue(stringRequest);*/
        Call<ResponseBody> responseBody = elearningAPI.getAllCategory();
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String jsonString = response.body().string();
                    java.lang.reflect.Type collectionType = new TypeToken<ArrayList<CategoryAll>>() {}.getType();
                   /* Collection<CategoryAll> enums = gson.fromJson(jsonString,collectionType);
                    CategoryAll[] categoryAllsResult = enums.toArray(new CategoryAll[enums.size()]);*/
                   ArrayList<CategoryAll> categoryAlls = gson.fromJson(jsonString,collectionType);

                    setAllCat(categoryAlls);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
    private void setAllCat(ArrayList<CategoryAll> cats){

        cat_all = cats;
        addCategory();
    }

    public void addCategory(){
        category_name = new ArrayList<String>();

       Collections.sort(cat_all, new Comparator<CategoryAll>() {
           @Override
           public int compare(CategoryAll o1, CategoryAll o2) {
               return o1.getCategoryName().compareTo(o2.getCategoryName());
           }
       });

//        Log.d("JSON","Data Cat ALL"+cat_all[0].getCategoryName()+" "+cat_all[1].getId()+" "+cat_all[0].getCourseList().size());
        if(cat_all != null){
            for (int i = 0; i < cat_all.size(); i++) {
                if(cat_all.get(i).getCourseList() != null)
                    category_name.add(cat_all.get(i).getCategoryName()+" ("+cat_all.get(i).getCourseList().size()+")");
                else
                    category_name.add(cat_all.get(i).getCategoryName()+" (0)");
            }
        }else {
            category_name.add("Test my CAT 1");
            category_name.add("Test my CAT 2");
            category_name.add("Test my CAT 3");
        }

        adapter_array = new ArrayAdapter(this,R.layout.lit_item_category,category_name);
        mListView.setAdapter(adapter_array);

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
                Log.d("JSON","##"+courseResult[0].getCourses().get(2).getName());

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
        AHBottomNavigationItem searchItem = new AHBottomNavigationItem("Search",R.drawable.ic_search);
        AHBottomNavigationItem myCourseItem = new AHBottomNavigationItem("MyCourse",R.drawable.ic_school);
        AHBottomNavigationItem loginItem = new AHBottomNavigationItem("Login",R.drawable.ic_input);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem("Profile",R.drawable.ic_account);
        SharedPreferences sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        isCheckAfLogin = sp.getBoolean("checkLogin",false);
        Log.d("JSON","####TesIsCheckOnCreateItem "+isCheckAfLogin);
       if(!isCheckAfLogin){
           bottomNavigationItem.addItem(homeItem);
           bottomNavigationItem.addItem(searchItem);
           bottomNavigationItem.addItem(loginItem);
       }else {


           bottomNavigationItem.addItem(homeItem);
           bottomNavigationItem.addItem(searchItem);
           bottomNavigationItem.addItem(myCourseItem);
           bottomNavigationItem.addItem(profileItem);

       }
//        bottomNavigationItem.addItem(myCourseItem);



        bottomNavigationItem.setDefaultBackgroundColor(Color.parseColor("#045757"));
        isCheckForEnroll = sp.getBoolean("check_for_enroll",false);
        Log.d("JSON","####Test to login "+isCheckForEnroll);
        if(!isCheckForEnroll){
            bottomNavigationItem.setCurrentItem(0);
        }else {
            bottomNavigationItem.setCurrentItem(2);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case android.R.id.home :
                myDrawer.openDrawer(GravityCompat.START);
                return true;
        }
//        if(id == R.id.search){
//            Toast.makeText(this,"Hello Search",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,SearchActivity.class);
//            intent.putExtra("key_name",course_new);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }
}
