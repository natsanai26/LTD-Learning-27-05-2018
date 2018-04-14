package com.example.windows10.ltd_learning;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by natsanai on 3/19/2018.
 */

class IsRegis2 {
    /**
     * response : {"status":true,"message":"registered"}
     * rating : 3.5
     * course : {"id":25,"categoryId":19,"name":"Engineering Material","detail":"covalent and metallic bond, crystal systems","sectionList":[{"id":28,"courseId":25,"name":"Untitled","parentSectionId":null,"content":"test_01","contentType":"TEXT","rank":1,"sub-section":[{"id":29,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"New test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"name":"Untitled","parentSectionId":29,"content":"High","contentType":"TEXT","rank":1,"sub-section":[]}]},{"id":30,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"Pxifbc_Wgz8MSQH0xbyaIg","contentType":"VIDEO","rank":1,"sub-section":[]}]}],"createdDate":1501779600000,"teacher":{"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null},"rating":3.16667,"enabled":true,"viewable":true}
     * isRegis : true
     */

    private Response response;
    @SerializedName("userRating")
    private double rating;
    private Course course;
    private boolean isRegis;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isIsRegis() {
        return isRegis;
    }

    public void setIsRegis(boolean isRegis) {
        this.isRegis = isRegis;
    }

    public static class Response {
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

    public static class Course {
        /**
         * id : 25
         * categoryId : 19
         * name : Engineering Material
         * detail : covalent and metallic bond, crystal systems
         * sectionList : [{"id":28,"courseId":25,"name":"Untitled","parentSectionId":null,"content":"test_01","contentType":"TEXT","rank":1,"sub-section":[{"id":29,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"New test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"name":"Untitled","parentSectionId":29,"content":"High","contentType":"TEXT","rank":1,"sub-section":[]}]},{"id":30,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"Pxifbc_Wgz8MSQH0xbyaIg","contentType":"VIDEO","rank":1,"sub-section":[]}]}]
         * createdDate : 1501779600000
         * teacher : {"idmember":137,"name":"Sumit","surname":"Mejan","username":"q3","passwd":"$2a$10$b4ymHRHAGk2cspPrSJWBfOrwFIdoipQ64/SOmyyZsPBSE2cOwEBI6","email":"q3@gmail.com","socialId":null,"socialType":null,"photoUrl":"https://lh6.googleusercontent.com/-Jn90tvQ4880/AAAAAAAAAAI/AAAAAAAAAGs/ho8LQp6y7T4/photo.jpg","type":"teacher","profile":null}
         * rating : 3.16667
         * enabled : true
         * viewable : true
         */

        private int id;
        private int categoryId;
        private String name;
        private String detail;
        private long createdDate;
        private Teacher teacher;
        private double rating;
        private boolean enabled;
        private boolean viewable;
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

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
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

        public List<SectionListBean> getSectionList() {
            return sectionList;
        }

        public void setSectionList(List<SectionListBean> sectionList) {
            this.sectionList = sectionList;
        }

        public static class Teacher {
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

            @SerializedName("idmember")
            private int memberId;
            private String name;
            private String surname;
            private String username;
            @SerializedName("passwd")
            private String password;
            private String email;
            private Object socialId;
            private Object socialType;
            private String photoUrl;
            private String type;
            private Object profile;

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
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
             * id : 28
             * courseId : 25
             * name : Untitled
             * parentSectionId : null
             * content : test_01
             * contentType : TEXT
             * rank : 1
             * sub-section : [{"id":29,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"New test_02","contentType":"TEXT","rank":1,"sub-section":[{"id":36,"courseId":25,"name":"Untitled","parentSectionId":29,"content":"High","contentType":"TEXT","rank":1,"sub-section":[]}]},{"id":30,"courseId":25,"name":"Untitled","parentSectionId":28,"content":"Pxifbc_Wgz8MSQH0xbyaIg","contentType":"VIDEO","rank":1,"sub-section":[]}]
             */

            private int id;
            private int courseId;
            private String name;
            private Object parentSectionId;
            private String content;
            private String contentType;
            private int rank;
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
                 * name : Untitled
                 * parentSectionId : 28
                 * content : New test_02
                 * contentType : TEXT
                 * rank : 1
                 * sub-section : [{"id":36,"courseId":25,"name":"Untitled","parentSectionId":29,"content":"High","contentType":"TEXT","rank":1,"sub-section":[]}]
                 */

                private int id;
                private int courseId;
                private String name;
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
                     * name : Untitled
                     * parentSectionId : 29
                     * content : High
                     * contentType : TEXT
                     * rank : 1
                     * sub-section : []
                     */

                    private int id;
                    private int courseId;
                    private String name;
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

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
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
        }
    }
}
