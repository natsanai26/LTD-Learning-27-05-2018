package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.windows10.ltd_learning.mModel.Course;
import com.example.windows10.ltd_learning.mActivity.CourseDetail;
import com.example.windows10.ltd_learning.mActivity.CourseDetailForMyCourse;

import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.squareup.picasso.Picasso;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/stream?content=";
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
//        imageView = (ImageView) v.findViewById(R.id.imageView_home);
//        getURLPic("");
//        getImageCourse();
        return  mViewHolder;
//        return new ViewHolder(LayoutInflater.from(parent.getContext())
//               .inflate(R.layout.model, parent, false));
    }

    public void getURLPic(String content, final ViewHolder holder){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+response);
                getImageCourse(response, holder);
            }

            private void getImageCourse(String response, ViewHolder holder) {
                String url = "http://158.108.207.7:8080/";
                Glide.with(mContext).load(url+response).fitCenter().into(holder.imageView);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", "Error JSON");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", "key999");
                return params;
            }
        };
        MySingleton.getInstance(mContext).addToReauestQue(stringRequest);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course.CoursesBean course = (Course.CoursesBean) mCourse.get(position);
        String content_get_pic;
        if(course.getTeacherProfile() != null)
            Picasso.with(mContext).load(course.getTeacherProfile()).into(holder.image_teacher);
        holder.teacherName.setText(course.getTeacherName()+" "+course.getTeacherSurname());
        holder.ratingBar.setRating((float) course.getRating());
        holder.nameTextView.setText(course.getName());
        content_get_pic = course.getContent_pic();
        Log.d("JSON", "In Adapter "+content_get_pic+" T-Name "+course.getTeacherName());
        if(content_get_pic != null){
            getURLPic(content_get_pic,holder);
        }
    }

    @Override
    public int getItemCount() {
        return mCourse.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView teacherName;
        public ImageView imageView,image_teacher;
        public RatingBar ratingBar;
        private SharedPreferences sharedPreferences;
        private int idUser;
        private boolean isEnroll;
        private static final String MyPREFERENCES = "MyPrefs" ;
        public TextView nameTextView;

        private List<Course> courses = new ArrayList<>();
        private Context ctx;

        public ViewHolder(View itemView,Context context,List<Course> courses) {
            super(itemView);
            this.courses = courses;
            this.ctx = context;
            image_teacher = itemView.findViewById(R.id.image_teacher);
            teacherName = itemView.findViewById(R.id.nameTxt_teacher);
            sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTxt);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_home);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("destroyInCourseDetail",false);
            editor.commit();
            idUser = sharedPreferences.getInt("idMember",-1);
            isEnroll = sharedPreferences.getBoolean("checkEnroll",false);
            Course.CoursesBean course_onclick = (Course.CoursesBean) this.courses.get(position);
            Intent intent = new Intent(this.ctx,CourseDetail.class);
            Intent intent2 = new Intent(this.ctx, CourseDetailForMyCourse.class);
            intent.putExtra("course_rating",course_onclick.getRating());
            intent.putExtra("course_voter",course_onclick.getVoter());
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