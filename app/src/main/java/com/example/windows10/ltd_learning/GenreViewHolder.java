package com.example.windows10.ltd_learning;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.windows10.ltd_learning.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class GenreViewHolder extends GroupViewHolder
{

  private TextView genreTitle;

  public GenreViewHolder(View itemView) {
    super(itemView);
    genreTitle = itemView.findViewById(R.id.lblListHeader);
  }

  @Override
  public void expand() {
    Log.d("JSON","Expand");
    super.expand();
  }

  @Override
  public void collapse() {
    Log.d("JSON","Collapse");
    super.collapse();
  }

  public void setGenreTitle(ExpandableGroup group) {
    genreTitle.setText(group.getTitle());
  }


}