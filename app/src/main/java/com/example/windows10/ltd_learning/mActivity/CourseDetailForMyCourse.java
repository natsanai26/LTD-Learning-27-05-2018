package com.example.windows10.ltd_learning.mActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.windows10.ltd_learning.R;

/**
 * Created by Windows10 on 1/29/2018.
 */

public class CourseDetailForMyCourse extends AppCompatActivity {
    private TextView tx_name,tx_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_d_mycourse_layout);
        tx_name = (TextView) findViewById(R.id.course_name_mycourse);
        tx_detail = (TextView)findViewById(R.id.detail_mycourse);
        tx_name.setText("Course Name : "+getIntent().getStringExtra("course_name"));
    }
}
