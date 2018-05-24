package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.windows10.ltd_learning.MyAPI;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mActivity.CourseDetail;
import com.example.windows10.ltd_learning.mModel.Course;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Windows10 on 5/23/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>
{
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/stream?content=";
    private Context mContext;
    private List<Course.CoursesBean> mData;
    private SharedPreferences sharedPreferences;

    public HomeAdapter(Context context,List<Course.CoursesBean> courses)
    {
        this.mContext = context;
        this.mData = courses;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.courseName.setText(mData.get(position).getName());
        holder.ratingBar.setRating((float) mData.get(position).getRating());
        holder.teacherName.setText(mData.get(position).getTeacher().getName()+" "+mData.get(position).getTeacher().getSurname());
        /*if((int)mData.get(position).getVoter()<=1)
            holder.numberText.setText(mData.get(position).getRating()+" from "+(int)mData.get(position).getVoter()+" vote");
        else
            holder.numberText.setText(mData.get(position).getRating()+" from "+(int)mData.get(position).getVoter()+" votes");
            */
        //holder.progressBar.setVisibility(View.INVISIBLE);
//        Log.d("JSON","Check Percent "+mData.get(position).getProgress().getSectionId());
        if (mData.get(position).getTeacher().getPhotoUrl()!=null){
            if (mData.get(position).getTeacher().getPhotoUrl().contains("https://")){
                Picasso.with(mContext).load(mData.get(position).getTeacher().getPhotoUrl()).into(holder.image_teacher);
            }
        else {
                Log.d("Image","check "+mData.get(position).getTeacher().getPhotoUrl());
                Picasso.with(mContext).load(MyAPI.BASE_URL_ELEARNNING+"elearning/"+mData.get(position).getTeacher().getPhotoUrl()).into(holder.image_teacher);
            }
        }
        String content_get_pic = null;
        if(mData.get(position).getSectionList().size() != 0){
            content_get_pic = mData.get(position).getSectionList().get(0).getContent();
            Log.d("JSON", "In CustomAdapter "+content_get_pic);
        }
        if(content_get_pic != null){
            getURLPic(content_get_pic,holder);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.courseName.setText(mData.get(position).getName());
                String MyPREFERENCES = "MyPrefs" ;
                sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("destroyInCourseDetail",false);
                Course.CoursesBean course_onclick = mData.get(position);

                //List<?> sectionList = course_onclick.getSectionList();
                Intent intent = new Intent(mContext,CourseDetail.class);
                //Log.d("JSON","##>>From onClickCourse"+sectionList);
                intent.putExtra("course_id",course_onclick.getId());
                Log.d("JSON","##>>From onClickCourse"+course_onclick.getId());
                if(course_onclick.getProgress() != null){
                    Log.d("JSON","##>>From onClickCourse"+course_onclick.getProgress().getSectionId());
                    intent.putExtra("progress_section_id",course_onclick.getProgress().getSectionId());
                    editor.putInt("id_section_progress",course_onclick.getProgress().getSectionId());
                }
                editor.commit();
                intent.putExtra("course_name",course_onclick.getName());
                intent.putExtra("course_rating",course_onclick.getRating());
                intent.putExtra("course_voter",course_onclick.getVoter());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void getURLPic(String content, final HomeAdapter.ViewHolder holder){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+response);
                getImageCourse(response, holder);
            }

            private void getImageCourse(String response, HomeAdapter.ViewHolder holder) {
                String url = "http://158.108.207.7:8080/";
                Picasso.with(mContext).load(url+response).placeholder(R.drawable.loading4).error(R.drawable.maxresdefault).into(holder.imageView);
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
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView teacherName;
        public ImageView imageView,image_teacher;
        public RatingBar ratingBar;
        public TextView courseName;

        public ViewHolder(View itemView) {
            super(itemView);
            image_teacher = itemView.findViewById(R.id.image_teacher);
            teacherName = itemView.findViewById(R.id.nameTxt_teacher);
            courseName = (TextView) itemView.findViewById(R.id.nameTxt);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_home);
        }
    }
}
