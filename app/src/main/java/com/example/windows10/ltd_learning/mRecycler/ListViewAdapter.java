package com.example.windows10.ltd_learning.mRecycler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.windows10.ltd_learning.CourseById;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mFragment.DetailCatFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows10 on 2/21/2018.
 */

public class ListViewAdapter extends ArrayAdapter<CourseById>{
    Context context;
    int layoutResourceId;
    ArrayList<CourseById> data = null;

    public ListViewAdapter (Context context, int resource, List<CourseById> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = (ArrayList) objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HeaderHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new HeaderHolder();
            holder.from = (TextView) row.findViewById(R.id.fromTextView);
            holder.to = (TextView)row.findViewById(R.id.toTextView);
            holder.subject = (TextView)row.findViewById(R.id.subjectTextView);

            row.setTag(holder);
        }
        else
        {
            holder = (HeaderHolder) row.getTag();
        }

        CourseById.CoursesBean item = (CourseById.CoursesBean) data.get(position);
        holder.from.setText(item.getName());
        holder.to.setText(item.getDetail());
        holder.subject.setText(item.getId());

        return row;
    }

    private class HeaderHolder {
        public TextView from;
        public TextView to;
        public TextView subject;

    }
}
