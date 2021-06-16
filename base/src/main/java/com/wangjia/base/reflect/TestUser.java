package com.wangjia.base.reflect;

/**
 * @author : wangjia
 * @time : 2018/4/28 17:27
 */
public class TestUser {
    private long id;
    private int age;
    private String name;

    public TestUser(long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public TestUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
