package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.windows10.ltd_learning.Comment;
import com.example.windows10.ltd_learning.CommentActivity;
import com.example.windows10.ltd_learning.CourseDetail;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.RpliedCommentActivity;

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

    private String MyPREFERENCES = "MyPrefs" ;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public CommentAdapter(Context context, List<Comment> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==Comment.MY_COMMENT_TYPE)
            view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_comment_right, parent, false);
        else
            view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_comment, parent, false);

        CommentAdapter.MyViewHolder myViewHolder = new CommentAdapter.MyViewHolder(view);
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
    public void onBindViewHolder(final CommentAdapter.MyViewHolder holder, final int position) {

        Date date = new Date(mData.get(position).getEditTime());
        holder.textDetail.setText(mData.get(position).getMsg());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        final String reportDate = df.format(date);
        Log.d("JSON","+++"+reportDate);
        holder.date.setText(reportDate);
        String MyPREFERENCES = "MyPrefs" ;
        sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int id_comment = sharedPreferences.getInt("id_comment",-1);
        String name_from_profile = sharedPreferences.getString("pName","not found");
        Log.d("JSON","+++"+id_comment);
        int id_check = mData.get(position).getMember().getIdmember();
        String name_check = mData.get(position).getMember().getName();

        Log.d("JSON","+++>>>>>"+id_comment+"  "+name_from_profile+"---"+name_check);
        holder.username.setText(mData.get(position).getMember().getName());
        if(mData.get(position).getSubdialogues().size() != 0){
            holder.textReplied.setText("View "+mData.get(position).getSubdialogues().size()+" Replies");
        }else {
            holder.textReplied.setText("Reply");
        }
        holder.textReplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RpliedCommentActivity.class);
                intent.putExtra("id_parent",mData.get(position).getIddialogue());
                intent.putExtra("text_detail",mData.get(position).getMsg());
                intent.putExtra("username_parent",mData.get(position).getMember().getName());
                intent.putExtra("string_date",reportDate);
                mContext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_box;
        public RelativeLayout relativeLayout;
        public long ticks;
        public TextView textReplied;
        public TextView username;
        public TextView date;
        public TextView textDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            image_box = itemView.findViewById(R.id.box);
            textReplied = itemView.findViewById(R.id.text_replied);
            relativeLayout = itemView.findViewById(R.id.relative_lay);
            username = itemView.findViewById(R.id.user_name);
            date = itemView.findViewById(R.id.date);
            textDetail = itemView.findViewById(R.id.textDetail);
        }
    }

}
