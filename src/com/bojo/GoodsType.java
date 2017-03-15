package com.bojo;

/**
 * Created by 李浩然 on 2017/1/8.
 */
public class GoodsType {

    /**
     * id
     */
    private String id;
    private String name;

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

    @Override
    public String toString() {
        return "GoodsType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
