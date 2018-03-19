package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 3/13/2018.
 */

public class CourseIdOnce {

    /**
     * response : {"status":true,"message":"request successfully"}
     * course : {"id":171,"categoryId":36,"name":"Smart SME","detail":"Smart SME","createdDate":1520321019000,"teacher":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null},"rating":5,"sectionList":[{"sectionId":172,"fileId":null,"courseId":171,"parentId":null,"name":null,"content":"QcmUSK269EwbRuXR4CKeog","contentType":null,"rank":0,"sub-section":[]},{"sectionId":173,"fileId":null,"courseId":171,"parentId":null,"name":"Intro ","content":null,"contentType":null,"rank":1,"sub-section":[{"sectionId":174,"fileId":175,"courseId":171,"parentId":173,"name":"Lesson 1","content":"sUCEY1mAuI1cdHZ7_Ewd7A","contentType":"VIDEO","rank":1,"sub-section":[]},{"sectionId":176,"fileId":177,"courseId":171,"parentId":173,"name":"Lesson 2","content":"xz6kn3ZY8bhnV5YcG4AgbQ","contentType":"VIDEO","rank":2,"sub-section":[]},{"sectionId":178,"fileId":179,"courseId":171,"parentId":173,"name":"Lesson 3 ","content":"38VCYGZ_hOuBitlkckVKHg","contentType":"VIDEO","rank":3,"sub-section":[]}]},{"sectionId":180,"fileId":null,"courseId":171,"parentId":null,"name":"Section 1 : What Is SME?","content":null,"contentType":null,"rank":2,"sub-section":[{"sectionId":181,"fileId":182,"courseId":171,"parentId":180,"name":"Lesson 4","content":"qGKB4UB499KCwBCjAfjHJQ","contentType":"VIDEO","rank":1,"sub-section":[]},{"sectionId":185,"fileId":186,"courseId":171,"parentId":180,"name":"Lesson 6","content":null,"contentType":"VIDEO","rank":2,"sub-section":[]},{"sectionId":183,"fileId":184,"courseId":171,"parentId":180,"name":"Lesson 5","content":"vOxzubzeZj4Vq84fhhIjyA","contentType":"VIDEO","rank":3,"sub-section":[]}]}],"enabled":false,"viewable":true}
     */

    private ResponseBean response;
    private CourseBean course;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
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

    public static class CourseBean {
        /**
         * id : 171
         * categoryId : 36
         * name : Smart SME
         * detail : Smart SME
         * createdDate : 1520321019000
         * teacher : {"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}
         * rating : 5.0
         * sectionList : [{"sectionId":172,"fileId":null,"courseId":171,"parentId":null,"name":null,"content":"QcmUSK269EwbRuXR4CKeog","contentType":null,"rank":0,"sub-section":[]},{"sectionId":173,"fileId":null,"courseId":171,"parentId":null,"name":"Intro ","content":null,"contentType":null,"rank":1,"sub-section":[{"sectionId":174,"fileId":175,"courseId":171,"parentId":173,"name":"Lesson 1","content":"sUCEY1mAuI1cdHZ7_Ewd7A","contentType":"VIDEO","rank":1,"sub-section":[]},{"sectionId":176,"fileId":177,"courseId":171,"parentId":173,"name":"Lesson 2","content":"xz6kn3ZY8bhnV5YcG4AgbQ","contentType":"VIDEO","rank":2,"sub-section":[]},{"sectionId":178,"fileId":179,"courseId":171,"parentId":173,"name":"Lesson 3 ","content":"38VCYGZ_hOuBitlkckVKHg","contentType":"VIDEO","rank":3,"sub-section":[]}]},{"sectionId":180,"fileId":null,"courseId":171,"parentId":null,"name":"Section 1 : What Is SME?","content":null,"contentType":null,"rank":2,"sub-section":[{"sectionId":181,"fileId":182,"courseId":171,"parentId":180,"name":"Lesson 4","content":"qGKB4UB499KCwBCjAfjHJQ","contentType":"VIDEO","rank":1,"sub-section":[]},{"sectionId":185,"fileId":186,"courseId":171,"parentId":180,"name":"Lesson 6","content":null,"contentType":"VIDEO","rank":2,"sub-section":[]},{"sectionId":183,"fileId":184,"courseId":171,"parentId":180,"name":"Lesson 5","content":"vOxzubzeZj4Vq84fhhIjyA","contentType":"VIDEO","rank":3,"sub-section":[]}]}]
         * enabled : false
         * viewable : true
         */

        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private long createdDate;
        private TeacherBean teacher;
        private double rating;
        private boolean enabled;
        private boolean viewable;
        private List<SectionList> sectionList;

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

        public TeacherBean getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherBean teacher) {
            this.teacher = teacher;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isViewable() {
            return viewable;
        }

        public void setViewable(boolean viewable) {
            this.viewable = viewable;
        }

        public List<SectionList> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<SectionList> sectionList) {
            this.sectionList = sectionList;
        }

        public static class TeacherBean {
            /**
             * idmember : 137
             * name : Sumit
             * surname : Mejan
             * username : q3
             * passwd : $2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6
             * email : q3@gmail.com
             * socialId : null
             * socialType : null
             * photoUrl : https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg
             * type : teacher
             * profile : null
             */

            private int idmember;
            private String name;
            private String surname;
            private String username;
            private String passwd;
            private String email;
            private Object socialId;
            private Object socialType;
            private String photoUrl;
            private String type;
            private Object profile;

            public int getIdmember() {
                return idmember;
            }

            public void setIdmember(int idmember) {
                this.idmember = idmember;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSurname() {
                return surname;
            }

            public void setSurname(String surname) {
                this.surname = surname;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPasswd() {
                return passwd;
            }

            public void setPasswd(String passwd) {
                this.passwd = passwd;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getSocialId() {
                return socialId;
            }

            public void setSocialId(Object socialId) {
                this.socialId = socialId;
            }

            public Object getSocialType() {
                return socialType;
            }

            public void setSocialType(Object socialType) {
                this.socialType = socialType;
            }

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getProfile() {
                return profile;
            }

            public void setProfile(Object profile) {
                this.profile = profile;
            }
        }

        public static class SectionListBean {
            /**
             * sectionId : 172
             * fileId : null
             * courseId : 171
             * parentId : null
             * name : null
             * content : QcmUSK269EwbRuXR4CKeog
             * contentType : null
             * rank : 0
             * sub-section : []
             */

            private int sectionId;
            private Object fileId;
            private int courseId;
            private Object parentId;
            private Object name;
            private String content;
            private Object contentType;
            private int rank;
            @SerializedName("sub-section")
            private List<?> subsection;

            public int getSectionId() {
                return sectionId;
            }

            public void setSectionId(int sectionId) {
                this.sectionId = sectionId;
            }

            public Object getFileId() {
                return fileId;
            }

            public void setFileId(Object fileId) {
                this.fileId = fileId;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getContentType() {
                return contentType;
            }

            public void setContentType(Object contentType) {
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
}
