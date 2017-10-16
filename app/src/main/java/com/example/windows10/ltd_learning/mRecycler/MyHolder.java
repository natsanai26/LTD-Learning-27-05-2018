package com.example.windows10.ltd_learning.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.windows10.ltd_learning.R;

/**
 * Created by Windows10 on 10/11/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder{

    TextView nametxt;

    public MyHolder(View itemView) {
        super(itemView);

        nametxt = (TextView) itemView.findViewById(R.id.nameTxt);
    }
}
