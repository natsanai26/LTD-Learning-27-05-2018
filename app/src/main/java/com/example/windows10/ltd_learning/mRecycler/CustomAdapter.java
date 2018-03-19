package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseDetail;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mFragment.CoursDetailFragment;

import java.util.List;

/**
 * Created by Windows10 on 2/6/2018.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context mContext;
    private List<Course.CoursesBean> mData;

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public CustomAdapter(Context context, List<Course.CoursesBean> data)
    {
        mContext = context;
        mData = data;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.courseName.setText(mData.get(position).getName());
        holder.ratingBar.setRating((float) mData.get(position).getRating());
        holder.teacherName.setText(mData.get(position).getTeacher().getName()+" "+mData.get(position).getTeacher().getSurname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.courseName.setText(mData.get(position).getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Course.CoursesBean course_onclick = mData.get(position);
                        //List<?> sectionList = course_onclick.getSectionList();
                        Intent intent = new Intent(mContext,CourseDetail.class);
                        //Log.d("JSON","##>>From onClickCourse"+sectionList);
                        intent.putExtra("course_id",course_onclick.getId());
                        Log.d("JSON","##>>From onClickCourse"+course_onclick.getId());
                        intent.putExtra("course_name",course_onclick.getName());
                        mContext.startActivity(intent);

//                CoursDetailFragment courseDetailFragment = new CoursDetailFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("name",mData[position].getName());
//                bundle.putString("detail",mData[position].getDetail());
//                courseDetailFragment.setArguments(bundle);
//                mTransaction.replace(R.id.content_id,courseDetailFragment).addToBackStack(null).commit();
                    }
                });
            }
        });
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView courseName;
        public RatingBar ratingBar;
        public TextView teacherName;
        public MyViewHolder(View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            teacherName = itemView.findViewById(R.id.teacher_name);
        }
    }
}