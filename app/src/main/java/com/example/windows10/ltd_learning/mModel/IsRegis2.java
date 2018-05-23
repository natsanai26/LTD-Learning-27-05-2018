package com.example.windows10.ltd_learning.mModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by natsanai on 3/19/2018.
 */

public class IsRegis2 {
    /**
     * response : {"status":true,"message":"registered"}
     * course : {"id":510,"categoryId":37,"name":"English2","detail":"<span style='color: rgb(255, 0, 0); font-family: thaisans_neueregular, Helvetica, sans-serif; font-size: 17px; letter-spacing: 0.36px; background-color: rgb(255, 255, 0);'>????????????????????????? ?????????????????????? 10,000 ?? ?????? 7+8 ?????? 750 ??? <\/span>","sectionList":[{"id":536,"courseId":510,"name":"Untitled","parentSectionId":null,"content":"sgYXbBmfqWDZ4oRPo36_Ww","contentType":"PICTURE","rank":0,"sub-section":[]},{"id":537,"courseId":510,"name":"Section","parentSectionId":null,"content":null,"contentType":"VIDEO","rank":1,"sub-section":[{"id":560,"courseId":510,"name":"Lesson1 ","parentSectionId":537,"content":"y4jLllb-3z90-OATJ8QI6w","contentType":"VIDEO","rank":1,"sub-section":[]}]}],"createdDate":1524548102000,"teacher":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":null},"rating":0,"voter":0,"enabled":true,"viewable":false,"progress":{"idprogress":122,"percent":100,"sectionId":560}}
     * isRegis : true
     * userRating : null
     */

    private ResponseBean response;
    private CourseBean course;
    private boolean isRegis;
    private Object userRating;

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

    public boolean isIsRegis() {
        return isRegis;
    }

    public void setIsRegis(boolean isRegis) {
        this.isRegis = isRegis;
    }

    public Object getUserRating() {
        return userRating;
    }

    public void setUserRating(Object userRating) {
        this.userRating = userRating;
    }

    public static class ResponseBean {
        /**
         * status : true
         * message : registered
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
         * id : 510
         * categoryId : 37
         * name : English2
         * detail : <span style='color: rgb(255, 0, 0); font-family: thaisans_neueregular, Helvetica, sans-serif; font-size: 17px; letter-spacing: 0.36px; background-color: rgb(255, 255, 0);'>????????????????????????? ?????????????????????? 10,000 ?? ?????? 7+8 ?????? 750 ??? </span>
         * sectionList : [{"id":536,"courseId":510,"name":"Untitled","parentSectionId":null,"content":"sgYXbBmfqWDZ4oRPo36_Ww","contentType":"PICTURE","rank":0,"sub-section":[]},{"id":537,"courseId":510,"name":"Section","parentSectionId":null,"content":null,"contentType":"VIDEO","rank":1,"sub-section":[{"id":560,"courseId":510,"name":"Lesson1 ","parentSectionId":537,"content":"y4jLllb-3z90-OATJ8QI6w","contentType":"VIDEO","rank":1,"sub-section":[]}]}]
         * createdDate : 1524548102000
         * teacher : {"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":null}
         * rating : 0
         * voter : 0
         * enabled : true
         * viewable : false
         * progress : {"idprogress":122,"percent":100,"sectionId":560}
         */

        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private long createdDate;
        private TeacherBean teacher;
        private double rating;
        private int voter;
        private boolean enabled;
        private boolean viewable;
        private ProgressBean progress;
        private List<SectionListBean> sectionList;

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

        public int getVoter() {
            return voter;
        }

        public void setVoter(int voter) {
            this.voter = voter;
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

        public ProgressBean getProgress() {
            return progress;
        }

        public void setProgress(ProgressBean progress) {
            this.progress = progress;
        }

        public List<SectionListBean> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<SectionListBean> sectionList) {
            this.sectionList = sectionList;
        }

        public static class TeacherBean {
            /**
             * idmember : 136
             * name : Somsak
             * surname : Chaipranee
             * username : q2
             * passwd : $2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi
             * email : q2@gmail.com
             * socialId : null
             * socialType : null
             * photoUrl : null
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

        public static class ProgressBean {
            /**
             * idprogress : 122
             * percent : 100
             * sectionId : 560
             */

            private int idprogress;
            private int percent;
            private int sectionId;

            public int getIdprogress() {
                return idprogress;
            }

            public void setIdprogress(int idprogress) {
                this.idprogress = idprogress;
            }

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }

            public int getSectionId() {
                return sectionId;
            }

            public void setSectionId(int sectionId) {
                this.sectionId = sectionId;
            }
        }

        public static class SectionListBean {
            /**
             * id : 536
             * courseId : 510
             * name : Untitled
             * parentSectionId : null
             * content : sgYXbBmfqWDZ4oRPo36_Ww
             * contentType : PICTURE
             * rank : 0
             * sub-section : []
             */

            private int id;
            private int courseId;
            private String name;
            private Object parentSectionId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public List<?> getSubsection() {
                return subsection;
            }

            public void setSubsection(List<?> subsection) {
                this.subsection = subsection;
            }
        }
    }
}
