package com.example.windows10.ltd_learning;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.windows10.ltd_learning.mFragment.HomeFragment;
import com.example.windows10.ltd_learning.mFragment.MyCouresFragment;
import com.example.windows10.ltd_learning.mFragment.ProfileFragment;
import com.example.windows10.ltd_learning.mFragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{
    AHBottomNavigation bottomNavigationItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationItem = (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigationItem.setOnTabSelectedListener(this);
        this.createNavItems();
    }

    private void createNavItems(){
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem("Home",R.drawable.ic_home);
        AHBottomNavigationItem searchItem = new AHBottomNavigationItem("Search",R.drawable.ic_search);
        AHBottomNavigationItem myCourseItem = new AHBottomNavigationItem("MyCourse",R.drawable.ic_school);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem("Profile",R.drawable.ic_account);

        bottomNavigationItem.addItem(homeItem);
        bottomNavigationItem.addItem(searchItem);
        bottomNavigationItem.addItem(myCourseItem);
        bottomNavigationItem.addItem(profileItem);

        bottomNavigationItem.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        bottomNavigationItem.setCurrentItem(0);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (position==0)
        {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,homeFragment).commit();
        }else  if (position==1)
        {
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,searchFragment).commit();
        }else  if (position==2)
        {
            MyCouresFragment myCouresFragment = new MyCouresFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,myCouresFragment).commit();
        }
        else if (position==3)
        {
            ProfileFragment profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,profileFragment).commit();
        }

        return true;
    }
}
