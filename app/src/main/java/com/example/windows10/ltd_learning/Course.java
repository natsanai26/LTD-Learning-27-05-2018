package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 10/17/2017.
 */

public class Course {
    /**
     * id : 25
     * categoryId : 19
     * name : Test01
     * detail : Test01
     * sectionList : [{"id":28,"courseId":25,"content":"test_01","contentType":"TEXT","rank":1,"sub-section":[{"id":29,"courseId":25,"parentSectionId":28,"content":"test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"parentSectionId":29,"content":"High","contentType":"TEXT","rank":1}]},{"id":30,"courseId":25,"parentSectionId":28,"content":"test_03","contentType":"TEXT","rank":2}]},{"id":29,"courseId":25,"parentSectionId":28,"content":"test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"parentSectionId":29,"content":"High","contentType":"TEXT","rank":1}]},{"id":30,"courseId":25,"parentSectionId":28,"content":"test_03","contentType":"TEXT","rank":2},{"id":36,"courseId":25,"parentSectionId":29,"content":"High","contentType":"TEXT","rank":1}]
     * createdDate : 1501779600000
     * key : key999
     */

    private int id;
    private int categoryId;
    private String name;
    private String detail;
    private long createdDate;
    private String key;
    private List<SectionListBean> sectionList;

    public Course(String courseName){
        name = courseName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<SectionListBean> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionListBean> sectionList) {
        this.sectionList = sectionList;
    }

    public static class SectionListBean {
        /**
         * id : 28
         * courseId : 25
         * content : test_01
         * contentType : TEXT
         * rank : 1
         * sub-section : [{"id":29,"courseId":25,"parentSectionId":28,"content":"test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"parentSectionId":29,"content":"High","contentType":"TEXT","rank":1}]},{"id":30,"courseId":25,"parentSectionId":28,"content":"test_03","contentType":"TEXT","rank":2}]
         * parentSectionId : 28
         */

        private int id;
        private int courseId;
        private String content;
        private String contentType;
        private int rank;
        private int parentSectionId;
        @SerializedName("sub-section")
        private List<SubsectionBeanX> subsection;

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

        public int getParentSectionId() {
            return parentSectionId;
        }

        public void setParentSectionId(int parentSectionId) {
            this.parentSectionId = parentSectionId;
        }

        public List<SubsectionBeanX> getSubsection() {
            return subsection;
        }

        public void setSubsection(List<SubsectionBeanX> subsection) {
            this.subsection = subsection;
        }

        public static class SubsectionBeanX {
            /**
             * id : 29
             * courseId : 25
             * parentSectionId : 28
             * content : test_02
             * contentType : TEXT
             * rank : 1
             * sub-section : [{"id":36,"courseId":25,"parentSectionId":29,"content":"High","contentType":"TEXT","rank":1}]
             */

            private int id;
            private int courseId;
            private int parentSectionId;
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

            public List<SubsectionBean> getSubsection() {
                return subsection;
            }

            public void setSubsection(List<SubsectionBean> subsection) {
                this.subsection = subsection;
            }

            public static class SubsectionBean {
                /**
                 * id : 36
                 * courseId : 25
                 * parentSectionId : 29
                 * content : High
                 * contentType : TEXT
                 * rank : 1
                 */

                private int id;
                private int courseId;
                private int parentSectionId;
                private String content;
                private String contentType;
                private int rank;

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
            }
        }
    }


//    private String mName;
//    private String mDetail;
//    private int mID;
//    private int mCatID;
//    private int mCreateDate;
//
//    public Course(String name){
//        mName = name;
//    }
//
//    public void setCreateDate(int createDate){
//        mCreateDate = createDate;
//    }
//
//    public int getCreateDate(){
//        return mCreateDate;
//    }
//    public void setDetail(String detail){
//        mDetail = detail;
//    }
//
//    public String getDetail(){
//        return mDetail;
//    }
//    public void setCatID(int id){
//        mCatID = id;
//    }
//    public int getCatID(){
//        return mCatID;
//    }
//
//    public void setID(int id){
//        mID = id;
//    }
//
//    public int getID(){
//        return mID;
//    }
//
//    public void setName(String name){
//        mName = name;
//    }
//    public String getName(){
//        return mName;
//    }

}
