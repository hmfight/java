package com.wangjia.mybatis.model;

/**
 * @author : wangjia
 * @time : 2018/1/8 21:39
 */
public class TestUser {
    private long id;
    private String userName;
    private int age;
    private String email;
    private long createTime;

    public TestUser(long id, String userName, int age, String email, long createTime) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.createTime = createTime;
    }

    public TestUser(String userName, int age, String email, long createTime) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.createTime = createTime;
    }

    public TestUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
