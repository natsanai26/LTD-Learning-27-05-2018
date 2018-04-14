package com.example.windows10.ltd_learning;

import com.example.windows10.ltd_learning.SectionList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Genre extends ExpandableGroup<SectionList.SubsectionBean> {

  public Genre(String title, List<SectionList.SubsectionBean> items) {
    super(title, items);
  }
}