package com.example.windows10.ltd_learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Windows10 on 12/5/2017.
 */

public class CourseDetail extends AppCompatActivity {
    private TextView tx_name,tx_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail_layout);
        tx_name = (TextView) findViewById(R.id.course_name);
        tx_detail = (TextView)findViewById(R.id.detail);
        tx_name.setText("Course Name : "+getIntent().getStringExtra("course_name"));
    }
}
