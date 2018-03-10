package com.example.windows10.ltd_learning.mFragment;

/**
 * Created by Windows10 on 2/6/2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows10.ltd_learning.R;

/**
 * Created by natsanai on 2/5/2018.
 */

public class CoursDetailFragment extends Fragment{
    private TextView name;
    private TextView detail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_detail_fragment,container,false);
        bindView(view);
        Bundle bundle = getArguments();
        name.setText(bundle.getString("name"));
        detail.setText(bundle.getString("detail"));
        return view;
    }
    public void bindView(View view)
    {
        name = view.findViewById(R.id.name);
        detail = view.findViewById(R.id.detail);
    }
}