package com.example.windows10.ltd_learning;

import java.util.List;

/**
 * Created by Windows10 on 2/12/2018.
 */

public class CourseTop {


    /**
     * courses : [{"id":27,"categoryId":36,"name":"Discrete mathematics","detail":"discrete function","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":5},{"id":28,"categoryId":35,"name":"SOA","detail":"easy","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":4},{"id":32,"categoryId":35,"name":"mit","detail":"sumit","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":4},{"id":25,"categoryId":19,"name":"Engineering Material","detail":"covalent and metallic bond, crystal systems","sectionList":[28,29,30,36,43],"createdDate":1501779600000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":3.5},{"id":56,"categoryId":37,"name":"Momentum","detail":"p=m.v","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":0}]
     * response : {"status":true,"message":"request successfully"}
     */

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

    public static class CoursesBean {
        /**
         * id : 27
         * categoryId : 36
         * name : Discrete mathematics
         * detail : discrete function
         * sectionList : []
         * createdDate : 1509469200000
         * key : key999
         * teacher : {"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}}
         * rating : 5.0
         */

        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private long createdDate;
        private String key;
        private TeacherBean teacher;
        private double rating;
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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
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

        public List<?> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<?> sectionList) {
            this.sectionList = sectionList;
        }

        public static class TeacherBean {
            /**
             * idteacher : 1
             * member : {"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}
             */

            private int idteacher;
            private MemberBean member;

            public int getIdteacher() {
                return idteacher;
            }

            public void setIdteacher(int idteacher) {
                this.idteacher = idteacher;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public static class MemberBean {
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
        }
    }
}
