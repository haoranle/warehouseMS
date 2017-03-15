package com.bojo;

import java.sql.Timestamp;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class WareIn {

    private String id;
    private String number;
    private Timestamp inTime;

    private Manager manager;

    @Override
    public String toString() {
        return "WareIn{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", inTime=" + inTime +
                ", manager=" + manager +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
