package com.example.windows10.ltd_learning;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchActivity extends AppCompatActivity{
    private MaterialSearchView searchView;
    private static Course course_all;
    private static final String URL_getAllCourse = "http://158.108.207.7:8090/elearning/course";
    private String[] course_new;
    private String[] course_S = {"Course ONE","Course TWO","Course TREE","Course FOUR","Course FIVE","Course SIX","Course SEVEN","Course EIGHT","Course NINE","Course TEN","Course TEN1","Course TEN2"};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        course_new = intent.getStringArrayExtra("key_name");
        if(course_new != null){
            Log.d("JSON--SEARCH","##"+course_new[20]);
        }
//        Bundle extras = getIntent().getExtras();
//        if (extras == null) {
//            finish();
//            return;
//        }

        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        searchView = (MaterialSearchView)findViewById(R.id.search_view_search);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,course_new);
        listView.setAdapter(adapter);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                listView = (ListView) findViewById(R.id.listview);
                ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,course_new);
                listView.setAdapter(adapter);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    String newText2 = newText.toUpperCase();
                    for(String item:course_new){
                        String item2 = item.toUpperCase();
                        if(item2.contains(newText2))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,lstFound);
                    listView.setAdapter(adapter);

                }
                else{
                    ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,course_new);
                    listView.setAdapter(adapter);
                }


                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.search_action);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
