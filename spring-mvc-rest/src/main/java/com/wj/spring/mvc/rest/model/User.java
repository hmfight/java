package com.wj.spring.mvc.rest.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * @author : wangjia
 * @time : 2017/12/25 22:56
 */
public class User {
    private long uid;
    private String username;
    @JSONField(serialize = false)
    private String password;

    /**
     * fastjson 序列化使用
     */
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
