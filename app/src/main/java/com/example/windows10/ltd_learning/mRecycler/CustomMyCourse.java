package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
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
import com.example.windows10.ltd_learning.mActivity.CourseDetail;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mModel.MyCourse;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Windows10 on 4/2/2018.
 */

public class CustomMyCourse extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private static final String URL_getURLPicture = "http://158.108.207.7:8080/api/stream?content=";
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private List<MyCourse.CoursesBean> mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public CustomMyCourse(Context context, List<MyCourse.CoursesBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item, parent, false);
        CustomAdapter.MyViewHolder myViewHolder = new CustomAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    public void getURLPic(String content, final CustomAdapter.MyViewHolder holder) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_getURLPicture + content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON", "url --> " + response);
                getImageCourse(response, holder);
            }

            private void getImageCourse(String response, CustomAdapter.MyViewHolder holder) {

                Glide.with(mContext).load(MyAPI.BASE_URL_COURSE_API + response).placeholder(R.drawable.loading4).error(R.drawable.maxresdefault).fitCenter().into(holder.imageView);
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
    public void onBindViewHolder(final CustomAdapter.MyViewHolder holder, final int position) {
        holder.courseName.setText(mData.get(position).getName());
        holder.ratingBar.setRating((float) mData.get(position).getRating());
        holder.teacherName.setText(mData.get(position).getTeacher().getName() + " " + mData.get(position).getTeacher().getSurname());
        if((int)mData.get(position).getVoter() <=1)
            holder.numberText.setText(mData.get(position).getRating() + " from " + (int)mData.get(position).getVoter() + " vote");
        else
            holder.numberText.setText(mData.get(position).getRating() + " from " + (int)mData.get(position).getVoter() + " votes");
        Log.d("PercentJ","Hello Percent ");
        if(mData.get(position).getProgress()!= null){
            String MyPREFERENCES = "MyPrefs";
            sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d("PercentJ","Check Percent "+mData.get(position).getProgress().getSectionId());
            double percent = mData.get(position).getProgress().getPercent();
            holder.progressBar.setProgress((int) percent);
//            editor.putInt("progressNow", (int) percent);
//            editor.commit();
            holder.progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#F86b00"), PorterDuff.Mode.SRC_IN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            }
        }
        else {
            holder.progressBar.setProgress(0);
        }
        String content_get_pic = null;
        if (mData.get(position).getSectionList().size() != 0) {
            content_get_pic = mData.get(position).getSectionList().get(0).getContent();
            Log.d("JSON", "In CustomAdapter " + content_get_pic);
        }
        if (content_get_pic != null) {
            getURLPic(content_get_pic, holder);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.courseName.setText(mData.get(position).getName());
                String MyPREFERENCES = "MyPrefs";
                sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                MyCourse.CoursesBean course_onclick = mData.get(position);

                //List<?> sectionList = course_onclick.getSectionList();
                Intent intent = new Intent(mContext, CourseDetail.class);
                //Log.d("JSON","##>>From onClickCourse"+sectionList);
                intent.putExtra("course_id", course_onclick.getId());
                Log.d("JSON", "##>>From onClickCourse" + course_onclick.getId());
                if (course_onclick.getProgress() != null) {
                    Log.d("JSON", "##>>From onClickCourse" + course_onclick.getProgress().getSectionId());
                    intent.putExtra("progress_section_id", course_onclick.getProgress().getSectionId());
                    editor.putInt("id_section_progress", course_onclick.getProgress().getSectionId());
                    editor.putInt("progressNow", (int) course_onclick.getProgress().getPercent());
                    editor.commit();
                }else {
                    editor.putInt("id_section_progress",-1);
                    editor.commit();
                }
                intent.putExtra("course_name", course_onclick.getName());
                intent.putExtra("course_rating", course_onclick.getRating());
                intent.putExtra("course_voter", course_onclick.getVoter());
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView numberText;
        public TextView courseName;
        public RatingBar ratingBar;
        public TextView teacherName;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            numberText = itemView.findViewById(R.id.number_vote);
            imageView = itemView.findViewById(R.id.rv_image);
            courseName = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            teacherName = itemView.findViewById(R.id.teacher_name);
        }
    }
}
