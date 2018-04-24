package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.windows10.ltd_learning.Comment;
import com.example.windows10.ltd_learning.CommentActivity;
import com.example.windows10.ltd_learning.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Windows10 on 4/24/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private SharedPreferences sharedPreferences;
    private static Context mContext;
    private static List<Comment> mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public CommentAdapter(Context context, List<Comment> data) {
        mContext = context;
        mData = data;

    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_comment, parent, false);
        CommentAdapter.MyViewHolder myViewHolder = new CommentAdapter.MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final CommentAdapter.MyViewHolder holder, final int position) {
        holder.ticks = System.currentTimeMillis()*10000 + mData.get(position).getEditTime();
        Log.d("JSON","+++"+holder.ticks);
        String s_date = String.valueOf(holder.ticks);
        Date date = new Date(Long.parseLong(s_date));
        holder.textDetail.setText(mData.get(position).getMsg());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String reportDate = df.format(date);
        Log.d("JSON","+++"+reportDate);
        holder.date.setText(reportDate);
        String MyPREFERENCES = "MyPrefs" ;
        sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int id_comment = sharedPreferences.getInt("id_comment",-1);
        String name_from_profile = sharedPreferences.getString("pName","not found");
        Log.d("JSON","+++"+id_comment);
        int id_check = mData.get(position).getMember().getIdmember();
        String name_check = mData.get(position).getMember().getName();
        if(id_comment == id_check ){
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }
        Log.d("JSON","+++>>>>>"+id_comment+"  "+name_from_profile+"---"+name_check);
        holder.username.setText(mData.get(position).getMember().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public long ticks;
        public TextView username;
        public TextView date;
        public TextView textDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relative_lay);
            username = itemView.findViewById(R.id.user_name);
            date = itemView.findViewById(R.id.date);
            textDetail = itemView.findViewById(R.id.textDetail);
        }
    }
}
