package com.example.windows10.ltd_learning.mModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Windows10 on 4/24/2018.
 */

public class Comment {

    /**
     * iddialogue : 4
     * courseId : 407
     * parentId : 3
     * member : {"idmember":138,"name":"natsanai2","surname":"Muang","username":"student","passwd":"$2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG","email":"nat.m@ku.th","socialId":null,"socialType":null,"photoUrl":null,"type":"student","profile":""}
     * msg : I bet the people who disliked are thinking of "dis I like"
     * editTime : 1524482926000
     * sub-dialogues : [{"iddialogue":4,"courseId":407,"parentId":3,"member":{"idmember":138,"name":"natsanai2","surname":"Muang","username":"student","passwd":"$2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG","email":"nat.m@ku.th","socialId":null,"socialType":null,"photoUrl":null,"type":"student","profile":""},"msg":"I bet the people who disliked are thinking of \"dis I like\"","editTime":1524482926000},{"iddialogue":5,"courseId":407,"parentId":3,"member":{"idmember":138,"name":"natsanai2","surname":"Muang","username":"student","passwd":"$2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG","email":"nat.m@ku.th","socialId":null,"socialType":null,"photoUrl":null,"type":"student","profile":""},"msg":"Brings back the old FIFA 15 vibes... The FIFA 17 songs are so crap :/ I just mute them and listen to this while I play","editTime":1524487498000}]
     */
    public static final int MY_COMMENT_TYPE = 0;
    public static final int OTHERS_COMMENT_TYPE = 1;
    private int iddialogue;
    private int courseId;
    private int parentId;
    private MemberBean member;
    private String msg;
    private long editTime;
    @SerializedName("sub-dialogues")
    private List<SubdialoguesBean> subdialogues;

    public int getIddialogue() {
        return iddialogue;
    }

    public void setIddialogue(int iddialogue) {
        this.iddialogue = iddialogue;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }

    public List<SubdialoguesBean> getSubdialogues() {
        return subdialogues;
    }

    public void setSubdialogues(List<SubdialoguesBean> subdialogues) {
        this.subdialogues = subdialogues;
    }

    public static class MemberBean {
        /**
         * idmember : 138
         * name : natsanai2
         * surname : Muang
         * username : student
         * passwd : $2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG
         * email : nat.m@ku.th
         * socialId : null
         * socialType : null
         * photoUrl : null
         * type : student
         * profile :
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

    public static class SubdialoguesBean {
        /**
         * iddialogue : 4
         * courseId : 407
         * parentId : 3
         * member : {"idmember":138,"name":"natsanai2","surname":"Muang","username":"student","passwd":"$2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG","email":"nat.m@ku.th","socialId":null,"socialType":null,"photoUrl":null,"type":"student","profile":""}
         * msg : I bet the people who disliked are thinking of "dis I like"
         * editTime : 1524482926000
         */

        private int iddialogue;
        private int courseId;
        private int parentId;
        private MemberBeanX member;
        private String msg;
        private long editTime;

        public int getIddialogue() {
            return iddialogue;
        }

        public void setIddialogue(int iddialogue) {
            this.iddialogue = iddialogue;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public MemberBeanX getMember() {
            return member;
        }

        public void setMember(MemberBeanX member) {
            this.member = member;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public long getEditTime() {
            return editTime;
        }

        public void setEditTime(long editTime) {
            this.editTime = editTime;
        }

        public static class MemberBeanX {
            /**
             * idmember : 138
             * name : natsanai2
             * surname : Muang
             * username : student
             * passwd : $2a$10$FLB7jHpRK5euMzX5QxYxtekpexf.hoF4aL1CKnaxVz.t1Zg6W49SG
             * email : nat.m@ku.th
             * socialId : null
             * socialType : null
             * photoUrl : null
             * type : student
             * profile :
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
