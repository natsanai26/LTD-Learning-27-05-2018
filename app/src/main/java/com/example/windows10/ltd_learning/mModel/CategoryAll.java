package com.example.windows10.ltd_learning.mModel;

import java.util.List;

/**
 * Created by Windows10 on 2/11/2018.
 */

public class CategoryAll {

    /**
     * id : 19
     * courseList : [25,38,39]
     * key : key999
     * categoryName : CategoryName19
     */

    private int id;
    private String key;
    private String categoryName;
    private List<Integer> courseList;

    public CategoryAll(String name,int id){
        this.categoryName = name;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Integer> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Integer> courseList) {
        this.courseList = courseList;
    }
}
