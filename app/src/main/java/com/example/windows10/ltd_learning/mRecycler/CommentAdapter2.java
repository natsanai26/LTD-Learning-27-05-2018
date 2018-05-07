package com.example.windows10.ltd_learning.mRecycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.windows10.ltd_learning.Comment;
import com.example.windows10.ltd_learning.R;

import java.util.List;

/**
 * Created by Windows10 on 4/24/2018.
 */

public class CommentAdapter2 extends BaseAdapter {
    private static Context mContext;
    private static List<Comment> mData;
    private MyViewHolder holder;

    public CommentAdapter2(Context context,List<Comment> data)
    {
        mContext = context;
        mData = data;
        Log.d("jjjj","+++++"+data.toString());
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_comment, viewGroup, false);
            holder = new MyViewHolder();
            holder.relativeLayout = view.findViewById(R.id.relative_lay);
            holder.username = view.findViewById(R.id.user_name);
            holder.date = view.findViewById(R.id.date);
            holder.textDetail = view.findViewById(R.id.textDetail);
            view.setTag(holder);
        }
        else
        {
            holder.textDetail.setText(mData.get(i).getMsg());
            holder.username.setText(mData.get(i).getMember().getName());
        }
        return view;
    }
    public static class MyViewHolder{
        public RelativeLayout relativeLayout;
        public long ticks;
        public TextView username;
        public TextView date;
        public TextView textDetail;


    }
}
