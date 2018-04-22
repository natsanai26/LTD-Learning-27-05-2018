package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 2/19/2018.
 */

public class CourseById {

    /**
     * courses : [{"id":27,"categoryId":36,"name":"Discrete mathematics","detail":"discrete function","sectionList":[],"createdDate":1509469200000,"teacher":null,"rating":0,"enabled":null,"viewable":null},{"id":25,"categoryId":19,"name":"Engineering Material","detail":"covalent and metallic bond, crystal systems","sectionList":[28,29,30,36,43],"createdDate":1501779600000,"teacher":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null},"rating":4,"enabled":true,"viewable":true},{"id":38,"categoryId":19,"name":"Chemistry","detail":"Chem","sectionList":[],"createdDate":1512147600000,"teacher":null,"rating":0,"enabled":null,"viewable":null}]
     * response : {"status":true,"message":"request successfully"}
     */
    @SerializedName("response")
    private ResponseBean response;
    private List<CoursesBean> courses;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public static class ResponseBean {
        /**
         * status : true
         * message : request successfully
         */

        private boolean status;
        private String message;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class CoursesBean extends CourseById {
        /**
         * id : 27
         * categoryId : 36
         * name : Discrete mathematics
         * detail : discrete function
         * sectionList : []
         * createdDate : 1509469200000
         * teacher : null
         * rating : 0.0
         * enabled : null
         * viewable : null
         */

        public CoursesBean(String courseName,int id){
            this.id = id;
            name = courseName;

        }
        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private long createdDate;
        private Object teacher;
        private double rating;
        private Object enabled;
        private Object viewable;
        private List<?> sectionList;

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

        public Object getTeacher() {
            return teacher;
        }

        public void setTeacher(Object teacher) {
            this.teacher = teacher;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public Object getEnabled() {
            return enabled;
        }

        public void setEnabled(Object enabled) {
            this.enabled = enabled;
        }

        public Object getViewable() {
            return viewable;
        }

        public void setViewable(Object viewable) {
            this.viewable = viewable;
        }

        public List<?> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<?> sectionList) {
            this.sectionList = sectionList;
        }
    }
}
