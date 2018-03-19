package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import android.view.inputmethod.CorrectionInfo;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseDetail;
import com.example.windows10.ltd_learning.CourseDetailForMyCourse;
import com.example.windows10.ltd_learning.ItemClickListener;
import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.SearchActivity;
import com.squareup.picasso.Picasso;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ImageView imageView;
    private List<Course> mCourse = new ArrayList<>();
    private boolean mHorizontal;
    private boolean mPager;
    private Context mContext;



    public Adapter(boolean horizontal, boolean pager, List<Course> course,Context context) {
        mHorizontal = horizontal;
        mCourse = course;
        mPager = pager;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        ViewHolder mViewHolder = new ViewHolder(v,mContext,mCourse);
        imageView = (ImageView) v.findViewById(R.id.imageView_home);
//        getImageCourse();
        return  mViewHolder;
//        return new ViewHolder(LayoutInflater.from(parent.getContext())
//               .inflate(R.layout.model, parent, false));
    }

    public void getImageCourse(){
        Picasso.with(mContext).load("http://158.108.207.7:8080/api/docImg/key999/48/46").into(imageView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course.CoursesBean course = (Course.CoursesBean) mCourse.get(position);
        holder.ratingBar.setRating((float) course.getRating());
        holder.nameTextView.setText(course.getName());

//        holder.nameTextView.setText(Integer.toString(mCourse.get(position).getId()));

//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(mContext,"Hello : "+mCourse.get(position),Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mCourse.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RatingBar ratingBar;
        private SharedPreferences sharedPreferences;
        private int idUser;
        private boolean isEnroll;
        private static final String MyPREFERENCES = "MyPrefs" ;
        public TextView nameTextView;
        private ItemClickListener itemClickListener;
        private List<Course> courses = new ArrayList<>();
        private Context ctx;

        public ViewHolder(View itemView,Context context,List<Course> courses) {
            super(itemView);
            this.courses = courses;
            this.ctx = context;
            sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTxt);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            idUser = sharedPreferences.getInt("idMember",-1);
            isEnroll = sharedPreferences.getBoolean("checkEnroll",false);
            Course.CoursesBean course_onclick = (Course.CoursesBean) this.courses.get(position);
            Intent intent = new Intent(this.ctx,CourseDetail.class);
            Intent intent2 = new Intent(this.ctx, CourseDetailForMyCourse.class);
            intent.putExtra("course_id",course_onclick.getId());
            intent.putExtra("course_name",course_onclick.getName());
            intent2.putExtra("course_id",course_onclick.getId());
            intent2.putExtra("course_name",course_onclick.getName());
            Log.d("JSON","##>>From onClickCourse"+course_onclick.getId()+"  "+idUser+" "+isEnroll);
//            if(isEnroll){
//                this.ctx.startActivity(intent2);
//            }
//            else {
//                this.ctx.startActivity(intent);
//            }
            this.ctx.startActivity(intent);
        }

    }

}