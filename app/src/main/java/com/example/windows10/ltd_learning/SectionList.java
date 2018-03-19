package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 3/12/2018.
 */

public class SectionList {

    /**
     * id : 173
     * courseId : 171
     * parentSectionId : null
     * content : Intro
     * contentType : TEXT
     * rank : 1
     * sub-section : [{"id":174,"courseId":171,"parentSectionId":173,"content":"Lesson 1","contentType":"TEXT","rank":1,"sub-section":[]},{"id":178,"courseId":171,"parentSectionId":173,"content":"Lesson 3 ","contentType":"TEXT","rank":3,"sub-section":[]},{"id":177,"courseId":171,"parentSectionId":173,"content":"xz6kn3ZY8bhnV5YcG4AgbQ","contentType":"VIDEO","rank":2,"sub-section":[]},{"id":176,"courseId":171,"parentSectionId":173,"content":"Lesson 2","contentType":"TEXT","rank":2,"sub-section":[]},{"id":179,"courseId":171,"parentSectionId":173,"content":null,"contentType":"VIDEO","rank":3,"sub-section":[]},{"id":175,"courseId":171,"parentSectionId":173,"content":"sUCEY1mAuI1cdHZ7_Ewd7A","contentType":"VIDEO","rank":1,"sub-section":[]}]
     */

    private int id;
    private int courseId;
    private Object parentSectionId;
    private String content;
    private String contentType;
    private int rank;
    @SerializedName("sub-section")
    private List<SubsectionBean> subsection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Object getParentSectionId() {
        return parentSectionId;
    }

    public void setParentSectionId(Object parentSectionId) {
        this.parentSectionId = parentSectionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<SubsectionBean> getSubsection() {
        return subsection;
    }

    public void setSubsection(List<SubsectionBean> subsection) {
        this.subsection = subsection;
    }

    public static class SubsectionBean {
        /**
         * id : 174
         * courseId : 171
         * parentSectionId : 173
         * content : Lesson 1
         * contentType : TEXT
         * rank : 1
         * sub-section : []
         */

        private int id;
        private int courseId;
        private int parentSectionId;
        private String content;
        private String contentType;
        private int rank;
        @SerializedName("sub-section")
        private List<?> subsection;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getParentSectionId() {
            return parentSectionId;
        }

        public void setParentSectionId(int parentSectionId) {
            this.parentSectionId = parentSectionId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public List<?> getSubsection() {
            return subsection;
        }

        public void setSubsection(List<?> subsection) {
            this.subsection = subsection;
        }
    }
}
