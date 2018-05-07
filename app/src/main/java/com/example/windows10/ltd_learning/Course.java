package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 10/17/2017.
 */

public class Course {


    /**
     * courses : [{"id":109,"categoryId":22,"name":"Engineering Material 2","detail":"The interdisciplinary field of materials science","sectionList":[],"createdDate":null,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":108,"categoryId":22,"name":"Introduction to API","detail":"Introduction to API","sectionList":[],"createdDate":null,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":0},{"id":107,"categoryId":22,"name":"Introduction to API2","detail":"Introduction to API","sectionList":[],"createdDate":null,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":0},{"id":106,"categoryId":22,"name":"Introduction to API3","detail":"Introduction to API","sectionList":[],"createdDate":null,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":57,"categoryId":37,"name":"general theory of relativity","detail":"Albert Einstein","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":56,"categoryId":37,"name":"Momentum","detail":"p=m.v","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":0},{"id":48,"categoryId":39,"name":"Hi2","detail":"Nantaporn2","sectionList":[45,46,47,48,49,50,51,52],"createdDate":1512147600000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":47,"categoryId":38,"name":"Golf2","detail":"Natsanai2","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":0},{"id":46,"categoryId":78,"name":"Mit2","detail":"Sumit2","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":45,"categoryId":35,"name":"Wax2","detail":"Suchao2","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":44,"categoryId":35,"name":"Art1","detail":"Watcharakorn1","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":43,"categoryId":36,"name":"Trigonometry","detail":"sin cos tan","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":42,"categoryId":36,"name":"graph theory","detail":"edges","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":40,"categoryId":22,"name":"golf1","detail":"Natsanai1","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":39,"categoryId":19,"name":"Electrical Chemistry","detail":"Na+ Cl-","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":38,"categoryId":19,"name":"Chemistry","detail":"Chem","sectionList":[],"createdDate":1512147600000,"key":"key999","teacher":null,"rating":0},{"id":37,"categoryId":37,"name":" Newton's law","detail":"sigma(F) = m.a","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":null,"rating":0},{"id":36,"categoryId":43,"name":"first","detail":"watcharakorn","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":null,"rating":0},{"id":35,"categoryId":38,"name":"to","detail":"suopashok","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":null,"rating":0},{"id":34,"categoryId":38,"name":"Astronomy","detail":"stars","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":33,"categoryId":39,"name":"golf","detail":"natsanai","sectionList":[37],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":0},{"id":32,"categoryId":35,"name":"mit","detail":"sumit","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":4},{"id":28,"categoryId":35,"name":"SOA","detail":"easy","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}},"rating":4},{"id":27,"categoryId":36,"name":"Discrete mathematics","detail":"discrete function","sectionList":[],"createdDate":1509469200000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":5},{"id":25,"categoryId":19,"name":"Engineering Material","detail":"covalent and metallic bond, crystal systems","sectionList":[28,29,30,36,43],"createdDate":1501779600000,"key":"key999","teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"rating":3.5}]
     * status : {"status":true,"message":"request successfully"}
     */
    @SerializedName("response")
    private StatusBean status;
    private List<CoursesBean> courses;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public static class StatusBean {
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

    public static class CoursesBean extends Course {
        /**
         * id : 109
         * categoryId : 22
         * name : Engineering Material 2
         * detail : The interdisciplinary field of materials science
         * sectionList : []
         * createdDate : null
         * key : key999
         * teacher : {"idteacher":2,"member":{"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}}
         * rating : 0.0
         */
        private String content_pic;
        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private Object createdDate;
        private String key;
        private TeacherBean teacher;
        private double rating;
        private Progress progress;
        private double voter=9;
        private List<SectionList> sectionList;
        private String teacher_name,teacher_surname,teacher_profile;

        public CoursesBean(){
        }
        public CoursesBean(String courseName,int id){
            this.id = id;
            name = courseName;

        }
        public CoursesBean(String courseName,int id,double rate){
            this.id = id;
            name = courseName;
            rating = rate;
        }
        public CoursesBean(String courseName,int id,double rate,String content){
            this.id = id;
            name = courseName;
            rating = rate;
            content_pic = content;
        }
        public CoursesBean(String courseName,int id,double rate,String content,double v){
            this.id = id;
            name = courseName;
            rating = rate;
            content_pic = content;
            this.voter = v;
        }
        public CoursesBean(String courseName,int id,double rate,String content,double v,String tname,String sname,String tpicture){
            teacher_profile = tpicture;
            teacher_surname = sname;
            teacher_name = tname;
            this.id = id;
            name = courseName;
            rating = rate;
            content_pic = content;
            this.voter = v;
        }
        public String getTeacherProfile(){return teacher_profile;}
        public String getTeacherName(){return teacher_name;}
        public String getTeacherSurname(){return teacher_surname;}

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

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
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

        public double getVoter() {
            return voter;
        }

        public void setVoter(double v){
            this.voter = v;
        }

        public Progress getProgress() {
            return progress;
        }

        public void setProgress(Progress progress) {
            this.progress = progress;
        }

        public void setTeacher(TeacherBean teacher) {
            this.teacher = teacher;
        }

        public double getRating() {
            return rating;
        }

        public void setContentPic(String content){
            content_pic = content;
        }

        public List<SectionList> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<SectionList> sectionList) {
            this.sectionList = sectionList;
        }

        public String getContent_pic(){
            return content_pic;
        }
        public void setRating(double rating) {
            this.rating = rating;
        }

        public static class TeacherBean {
            /**
             * idteacher : 2
             * member : {"idmember":136,"name":"Somsak","surname":"Chaipranee","username":"q2","passwd":"$2a$10$0JMHkyjZe2O8ZAbSbbw/z..U2EdEn.LK.Y3qq5RSOjGb8w4GnYwMi","email":"q2@gmail.com","socialId":null,"socialType":null,"photoUrl":null,"type":"teacher","profile":"xxx"}
             */

            private int idteacher;
            private MemberBean member;
            private String name;
            private String surname;

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
                 * profile : xxx
                 */

                private int idmember;
                private String name;
                private String surname;
                private String username;
                private String passwd;
                private String email;
                private Object socialId;
                private Object socialType;
                private Object photoUrl;
                private String type;
                private String profile;

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

                public Object getPhotoUrl() {
                    return photoUrl;
                }

                public void setPhotoUrl(Object photoUrl) {
                    this.photoUrl = photoUrl;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getProfile() {
                    return profile;
                }

                public void setProfile(String profile) {
                    this.profile = profile;
                }
            }
        }
    }
}


