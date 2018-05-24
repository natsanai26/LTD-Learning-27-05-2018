package com.example.windows10.ltd_learning.mFragment;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mInterface.ElearningAPI;



/**
 * Created by Windows10 on 10/11/2017.
 */

public class MyCouresFragment extends Fragment {

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition())
            {
                case 0:
                    getFragmentManager().beginTransaction().replace(R.id.my_course_content,new MyCourseCurrentFragment()).commit();
                    break;
                case 1:
                    getFragmentManager().beginTransaction().replace(R.id.my_course_content,new MyCourseFinishedFragment()).commit();
                    break;

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mycourse_frag_rv,container,false);
        if (savedInstanceState==null)
        {
            getFragmentManager().beginTransaction().add(R.id.my_course_content,new MyCourseCurrentFragment()).commit();
        }
        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#045757"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#045757"));

        return rootView;
    }


}
