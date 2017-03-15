package com.bojo;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class WareHouse {

    private String id;
    private String number;
    private String name;
    private String remarks;

    @Override
    public String toString() {
        return "WareHouse{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
