package com.wj.ref;

/**
 * @author : hmfight
 * @time : 2018/2/4 13:27
 */
public class MyObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject.finalize() called");
    }

    @Override
    public String toString() {
        return "alive";
    }
}