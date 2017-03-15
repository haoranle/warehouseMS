package com.bojo;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class Shelf {

    private String id;
    private String number;
    private String remarks;

    private WareHouse wareHouse;

    @Override
    public String toString() {
        return "Shelf{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", remarks='" + remarks + '\'' +
                ", wareHouse=" + wareHouse +
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }
}
