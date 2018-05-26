package com.example.windows10.ltd_learning.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.ltd_learning.R;

/**
 * Created by natsanai on 5/26/2018.
 */

public class CourseNotFoundFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_not_found_layout,container,false);
        return view;
    }
}
