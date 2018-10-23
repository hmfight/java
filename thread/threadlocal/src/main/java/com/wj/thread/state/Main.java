package com.wj.thread.state;

/**
 * @author : wangjia
 * @time : 2018/2/9 18:01
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.wait();
    }
}
