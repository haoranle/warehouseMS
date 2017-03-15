package com.bojo;

import com.view.contentPanel.GoodsPanel;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class Goods {

    private String id;
    private String name;
    private Double price;
    private String anount;
    private String remarks;

    private GoodsType goodsType;

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", anount='" + anount + '\'' +
                ", remarks='" + remarks + '\'' +
                ", goodsType=" + goodsType +
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAnount() {
        return anount;
    }

    public void setAnount(String anount) {
        this.anount = anount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }
}
