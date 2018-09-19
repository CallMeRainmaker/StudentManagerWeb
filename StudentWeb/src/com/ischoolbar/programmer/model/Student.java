package com.ischoolbar.programmer.model;

import java.io.InputStream;

public class Student {
    private int id;
    private String number;
    private int clazzId;
    private String sex;
    private String name;
    private String password;
    private String mobile;
    private String qq;
    private InputStream photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
