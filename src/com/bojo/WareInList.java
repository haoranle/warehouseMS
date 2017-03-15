package com.bojo;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class WareInList {

    private String id;
    private String amount;
    private String amountLess;

    private WareIn wareIn;
    private Goods goods;
    private Shelf shelf;
    private Supplier supplier;

    @Override
    public String toString() {
        return "WareInList{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", amountLess='" + amountLess + '\'' +
                ", wareIn=" + wareIn +
                ", goods=" + goods +
                ", shelf=" + shelf +
                ", supplier=" + supplier +
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

    public String getAmountLess() {
        return amountLess;
    }

    public void setAmountLess(String amountLess) {
        this.amountLess = amountLess;
    }

    public WareIn getWareIn() {
        return wareIn;
    }

    public void setWareIn(WareIn wareIn) {
        this.wareIn = wareIn;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
