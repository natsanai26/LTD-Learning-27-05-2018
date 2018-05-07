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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.windows10.ltd_learning.Comment;
import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.CourseDetail;
import com.example.windows10.ltd_learning.MySingleton;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.RpliedCommentActivity;
import com.example.windows10.ltd_learning.SingleDialogue;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Windows10 on 4/30/2018.
 */

public class RepliedAdapter extends RecyclerView.Adapter<RepliedAdapter.MyViewHolder>{
    private String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private List<SingleDialogue.SubdialoguesBean> mData;


    public RepliedAdapter(Context context, List<SingleDialogue.SubdialoguesBean> data)
    {
        mContext = context;
        mData = data;

    }
    @Override
    public RepliedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==SingleDialogue.MY_COMMENT_TYPE)
            view = LayoutInflater.from(mContext).inflate(R.layout.rv_replied_me, parent, false);
        else
            view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_replied, parent, false);

        RepliedAdapter.MyViewHolder myViewHolder = new RepliedAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int id_comment = sharedPreferences.getInt("id_comment",-1);
        if (id_comment==mData.get(position).getMember().getIdmember())
            return Comment.MY_COMMENT_TYPE;
        else
            return Comment.OTHERS_COMMENT_TYPE;
    }

    @Override
    public void onBindViewHolder(final RepliedAdapter.MyViewHolder holder, final int position) {
        Date date = new Date(mData.get(position).getEditTime());
        holder.textDetail.setText(mData.get(position).getMsg());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String reportDate = df.format(date);
        Log.d("JSON","+++"+reportDate);
        holder.date.setText(reportDate);
        holder.username.setText(mData.get(position).getMember().getName());
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textReplied;
        public TextView username;
        public TextView date;
        public TextView textDetail;
        public MyViewHolder(View itemView) {
            super(itemView);
            textReplied = itemView.findViewById(R.id.text_replied);
            username = itemView.findViewById(R.id.user_name);
            date = itemView.findViewById(R.id.date);
            textDetail = itemView.findViewById(R.id.textDetail);

        }
    }
}
