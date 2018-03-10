package com.example.windows10.ltd_learning;

/**
 * Created by Windows10 on 2/13/2018.
 */

public class IsRegis {


    /**
     * course : {"idcourse":1,"teacher":{"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}},"linkid":25,"enabled":true,"viewable":true,"totalrating":14,"avgrating":3.5}
     * isRegis : true
     */

    private CourseBean course;
    private boolean isRegis;

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

    public static class CourseBean {
        /**
         * idcourse : 1
         * teacher : {"idteacher":1,"member":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}}
         * linkid : 25
         * enabled : true
         * viewable : true
         * totalrating : 14.0
         * avgrating : 3.5
         */

        private int idcourse;
        private TeacherBean teacher;
        private int linkid;
        private boolean enabled;
        private boolean viewable;
        private double totalrating;
        private double avgrating;

        public int getIdcourse() {
            return idcourse;
        }

        public void setIdcourse(int idcourse) {
            this.idcourse = idcourse;
        }

        public TeacherBean getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherBean teacher) {
            this.teacher = teacher;
        }

        public int getLinkid() {
            return linkid;
        }

        public void setLinkid(int linkid) {
            this.linkid = linkid;
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

        public double getTotalrating() {
            return totalrating;
        }

        public void setTotalrating(double totalrating) {
            this.totalrating = totalrating;
        }

        public double getAvgrating() {
            return avgrating;
        }

        public void setAvgrating(double avgrating) {
            this.avgrating = avgrating;
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
