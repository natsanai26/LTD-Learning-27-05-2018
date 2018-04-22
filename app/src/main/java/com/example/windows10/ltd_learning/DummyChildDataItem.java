package com.example.windows10.ltd_learning;

import java.io.Serializable;

/**
 * <b></b>
 * <p>This class is used to </p>
 * Created by Rohit.
 */
public class DummyChildDataItem implements Serializable {
    private String childName;
    private String childContent;
    private String childContentType;
    private int childId;
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildContent() {
        return childContent;
    }

    public String getChildContentType() {
        return childContentType;
    }

    public void setChildContentType(String childContentType) {
        this.childContentType = childContentType;
    }

    public void setChildContent(String childContent) {
        this.childContent = childContent;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}