package com.example.windows10.ltd_learning;

import android.view.View;
import android.widget.TextView;

import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.SectionList;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubSectionViewHolder extends ChildViewHolder {

  private TextView subSectionName;

  public SubSectionViewHolder(View itemView) {
    super(itemView);
    subSectionName = itemView.findViewById(R.id.lblListItem);
  }

  public TextView getSubSectionName(){
    return subSectionName;
  }

  public void setSubSectionName(String name) {
    subSectionName.setText(name);
  }
}