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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.windows10.ltd_learning.mModel.Course;
import com.example.windows10.ltd_learning.mActivity.CourseDetail;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Windows10 on 2/6/2018.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/stream?content=";
    private SharedPreferences sharedPreferences;
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

    public void getURLPic(String content, final MyViewHolder holder){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON","url --> "+response);
                getImageCourse(response, holder);
            }

            private void getImageCourse(String response, MyViewHolder holder) {
                String url = "http://158.108.207.7:8080/";
                Picasso.with(mContext).load(url+response).fit().centerCrop().into(holder.imageView);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.courseName.setText(mData.get(position).getName());
        holder.ratingBar.setRating((float) mData.get(position).getRating());
        holder.teacherName.setText(mData.get(position).getTeacher().getName()+" "+mData.get(position).getTeacher().getSurname());
        holder.numberText.setText(mData.get(position).getRating()+" from "+mData.get(position).getVoter()+" votes");
        holder.progressBar.setVisibility(View.INVISIBLE);
//        Log.d("JSON","Check Percent "+mData.get(position).getProgress().getSectionId());
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;
        public TextView numberText;
        public TextView courseName;
        public RatingBar ratingBar;
        public TextView teacherName;
        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            numberText = itemView.findViewById(R.id.number_vote);
            imageView = itemView.findViewById(R.id.rv_image);
            courseName = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            teacherName = itemView.findViewById(R.id.teacher_name);
        }
    }
}