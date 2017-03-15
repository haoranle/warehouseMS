package com.bojo;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class WareOutList {

    private String id;
    private String amount;

    private WareOut wareOut;
    private Goods goods;
    private WareInList wareInList;

    @Override
    public String toString() {
        return "WareOutList{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", wareOut=" + wareOut +
                ", goods=" + goods +
                ", wareInList=" + wareInList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public WareOut getWareOut() {
        return wareOut;
    }

    public void setWareOut(WareOut wareOut) {
        this.wareOut = wareOut;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public WareInList getWareInList() {
        return wareInList;
    }

    public void setWareInList(WareInList wareInList) {
        this.wareInList = wareInList;
    }
}
