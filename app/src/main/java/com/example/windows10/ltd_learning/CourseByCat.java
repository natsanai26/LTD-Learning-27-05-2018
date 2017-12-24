package com.example.windows10.ltd_learning;

import java.util.List;

/**
 * Created by Windows10 on 12/5/2017.
 */

public class CourseByCat {

    /**
     * id : 38
     * courseList : [34,50,51]
     * key : key999
     * categoryName : กั๊กๆๆๆๆๆๆๆๆๆๆๆ
     */

    private int id;
    private String key;
    private String categoryName;
    private List<Integer> courseList;

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
