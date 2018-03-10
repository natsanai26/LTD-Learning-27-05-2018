package com.example.windows10.ltd_learning;

/**
 * Created by Windows10 on 2/4/2018.
 */

public class AddRegisPost {

    /**
     * idregis : 13
     * course : {"idcourse":3,"teacher":{"idteacher":2,"member":{"idmember":3,"name":"ป๋องแป๋ง","surname":"ยิ้มสวย","username":"pongpong","passwd":"p12345678","email":"pong@ku.th","type":"teacher","profile":"resume"}},"linkid":28,"enabled":true,"viewable":true,"totalrating":0,"avgrating":0}
     * member : {"idmember":121,"name":"","surname":"","username":"Alex","passwd":"12345","email":"@.com","type":"student","profile":null}
     * rating : null
     */

    private int idregis;
    private CourseBean course;
    private MemberBeanX member;
    private Object rating;

    public int getIdregis() {
        return idregis;
    }

    public void setIdregis(int idregis) {
        this.idregis = idregis;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public MemberBeanX getMember() {
        return member;
    }

    public void setMember(MemberBeanX member) {
        this.member = member;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public static class CourseBean {
        /**
         * idcourse : 3
         * teacher : {"idteacher":2,"member":{"idmember":3,"name":"ป๋องแป๋ง","surname":"ยิ้มสวย","username":"pongpong","passwd":"p12345678","email":"pong@ku.th","type":"teacher","profile":"resume"}}
         * linkid : 28
         * enabled : true
         * viewable : true
         * totalrating : 0
         * avgrating : 0
         */

        private int idcourse;
        private TeacherBean teacher;
        private int linkid;
        private boolean enabled;
        private boolean viewable;
        private int totalrating;
        private int avgrating;

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

        public int getTotalrating() {
            return totalrating;
        }

        public void setTotalrating(int totalrating) {
            this.totalrating = totalrating;
        }

        public int getAvgrating() {
            return avgrating;
        }

        public void setAvgrating(int avgrating) {
            this.avgrating = avgrating;
        }

        public static class TeacherBean {
            /**
             * idteacher : 2
             * member : {"idmember":3,"name":"ป๋องแป๋ง","surname":"ยิ้มสวย","username":"pongpong","passwd":"p12345678","email":"pong@ku.th","type":"teacher","profile":"resume"}
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
                 * idmember : 3
                 * name : ป๋องแป๋ง
                 * surname : ยิ้มสวย
                 * username : pongpong
                 * passwd : p12345678
                 * email : pong@ku.th
                 * type : teacher
                 * profile : resume
                 */

                private int idmember;
                private String name;
                private String surname;
                private String username;
                private String passwd;
                private String email;
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

    public static class MemberBeanX {
        /**
         * idmember : 121
         * name :
         * surname :
         * username : Alex
         * passwd : 12345
         * email : @.com
         * type : student
         * profile : null
         */

        private int idmember;
        private String name;
        private String surname;
        private String username;
        private String passwd;
        private String email;
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
