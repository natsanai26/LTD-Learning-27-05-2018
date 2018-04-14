package com.example.windows10.ltd_learning;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.SectionList;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, SubSectionViewHolder> {


  public GenreAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
    return new GenreViewHolder(view);
  }

  @Override
  public SubSectionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new SubSectionViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(SubSectionViewHolder holder, final int flatPosition, ExpandableGroup group,
                                    final int childIndex) {
    final SectionList.SubsectionBean subSection = ((Genre) group).getItems().get(childIndex);
    holder.setSubSectionName(subSection.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onItemClickListener.OnItemClick(view);
      }
    });


    if(((Genre)group)!=null) {
      Log.d("JSON",((Genre) group).getTitle());
      if (((Genre) group).getTitle().equals("Intro ") && childIndex <= 2) {
        holder.itemView.setBackgroundColor(Color.parseColor("#ff0000"));
      }
    }
  }



  @Override
  public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
      ExpandableGroup group) {
    holder.setGenreTitle(group);
  }

  interface OnItemClickListener{
    void OnItemClick(View view);
  }
  OnItemClickListener onItemClickListener;
  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }
}