package com.example.windows10.ltd_learning.mFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.ltd_learning.R;

/**
 * Created by Windows10 on 1/22/2018.
 */

public class NameCourseFragment extends Fragment {
    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_name_course, container, false);
        return rootView;
    }
}
