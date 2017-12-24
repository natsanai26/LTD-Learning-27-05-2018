package com.example.windows10.ltd_learning;

import java.util.List;

/**
 * Created by natsanai on 12/6/2017.
 */

public class Profile
{
    public int getIdmember() {
        return idmember;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public String getMtype() {
        return mtype;
    }

    public String getMpasswd() {
        return mpasswd;
    }

    public String getMusername() {
        return musername;
    }

    public String getMprofile() {
        return mprofile;
    }

    public String getMname() {
        return mname;
    }

    public String getMemail() {
        return memail;
    }

    private int idmember;
    private List<Teacher> teachers;
    private String mtype;
    private String mpasswd;
    private String musername;
    private String mprofile;
    private String msurname;

    public String getMsurname() {
        return msurname;
    }

    private String mname;
    private String memail;


}
