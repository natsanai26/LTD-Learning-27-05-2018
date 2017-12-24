package com.example.windows10.ltd_learning.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows10.ltd_learning.ItemClickListener;
import com.example.windows10.ltd_learning.R;

/**
 * Created by Windows10 on 10/11/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder{

    public TextView nametxt;
    private ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);

        nametxt = (TextView) itemView.findViewById(R.id.nameTxt);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
