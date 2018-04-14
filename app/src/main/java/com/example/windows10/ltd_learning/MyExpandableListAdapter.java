package com.example.windows10.ltd_learning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by natsanai on 3/19/2018.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter{
    private SharedPreferences sharedPreferences;
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<SectionList.SubsectionBean>> listHashMap;

    private ArrayList<Integer> listOfId = new ArrayList<Integer>();

    public MyExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<SectionList.SubsectionBean>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    public HashMap<String,List<SectionList.SubsectionBean>> getListHashMap()
    {
        return  listHashMap;
    }
    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String)getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = (TextView)view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        Log.d("section","From Group");

        return view;
    }

    @Override
    public View getChildView(final int i,final int i1, boolean b, View view, ViewGroup viewGroup) {
        boolean changeText = false;
        final String childText = ((SectionList.SubsectionBean)getChild(i,i1)).getName();
        final int childId = ((SectionList.SubsectionBean)getChild(i,i1)).getId();
        int checkId,sizeSection;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,null);
        }
        String MyPREFERENCES = "MyPrefs" ;
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        checkId = sharedPreferences.getInt("id_section_progress",-1);
        TextView txtListChild = (TextView)view.findViewById(R.id.lblListItem);
        Log.d("section","Id Progress "+childId);
        for (int j:listOfId)
        {
            if (childId==j)
            {
                changeText = true;
                break;
            }
        }
        if (!changeText) {
            listOfId.add(childId);
        }

        txtListChild.setText(childText);

        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChildClickListener.click(i,i1);

            }
        });

       Log.d("section","data list "+listOfId);
        for (int k = 0; k < listOfId.size();k++){
            if(checkId == listOfId.get(k)&& !changeText){
                Log.d("section","In loop "+checkId+" = "+listOfId.get(k));
                txtListChild.setBackgroundColor(Color.parseColor("#d1122f"));
                break;
            }
        }
        Log.d("section","From Child");
        return view;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener)
    {
        this.onChildClickListener = onChildClickListener;
    }
    private OnChildClickListener onChildClickListener;

    public void performClick(int parent,int child)
    {
        onChildClickListener.click(parent,child);
    }

    interface OnChildClickListener
    {
        void click(int parent,int child);
    }

}
