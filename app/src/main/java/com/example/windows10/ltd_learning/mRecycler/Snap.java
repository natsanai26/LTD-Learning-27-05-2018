package com.example.windows10.ltd_learning.mRecycler;

import com.example.windows10.ltd_learning.Course;

import java.util.List;

/**
 * Created by Windows10 on 10/17/2017.
 */

public class Snap {

    private int mGravity;
    private String mText;
    private List<Course> mCourse;

    public Snap(int gravity, String text, List<Course> courses){
        mGravity = gravity;
        mText = text;
        mCourse = courses;
    }
    public String getText(){return mText;}
    public int getGravity(){return mGravity;}
    public List<Course> getCourse(){return mCourse;}
}
