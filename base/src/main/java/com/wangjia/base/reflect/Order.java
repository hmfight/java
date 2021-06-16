package com.wangjia.base.reflect;

public class Order {
    private long id;
    private String info;
    private int status;

    public Order(long id, String info, int status) {
        this.id = id;
        this.info = info;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSame(Integer status) {
        return this.status == status;
    }
}