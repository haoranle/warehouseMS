package com.bojo;

import java.sql.Timestamp;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class Manager {

    private String id;
    private String name;
    private String account;
    private String passWord;
    private String phone;
    private String job;

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
