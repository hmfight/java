package com.wangjia.thread.sync.test;

import com.wangjia.thread.sync.ForSync;
import org.junit.Test;

/**
 * @author : wangjia
 * @time : 2018/2/9 11:07
 */
public class TestSync {

    /**
     * 多线程竞争容易一对象锁
     */
    @Test
    public void test1() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.syncMethod1()).start();
        new Thread(() -> forSync.syncMethod2()).start();
        Thread.sleep(5000);
    }

    /**
     * 多线程分别获取不同实例对象的锁
     */
    @Test
    public void test2() throws Exception {
        ForSync forSync1 = new ForSync();
        ForSync forSync2 = new ForSync();
        new Thread(() -> forSync1.syncMethod1()).start();
        new Thread(() -> forSync2.syncMethod2()).start();
        Thread.sleep(5000);
    }

    /**
     * 非 synchronized 方法和 synchronized 方法 互不影响
     */
    @Test
    public void test3() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.syncMethod1()).start();
        new Thread(() -> forSync.method3()).start();
        Thread.sleep(5000);
    }

    /**
     * synchronized (this) 和 synchronized (SomeClass.class)
     * 一个是实例对象锁 和 一个是 Class 锁
     */
    @Test
    public void test4() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.method6()).start();
        new Thread(() -> forSync.method9()).start();
        Thread.sleep(5000);
    }

    @Test
    public void test5() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.method6()).start();
        new Thread(() -> forSync.method9()).start();
        Thread.sleep(5000);
    }

    /**
     * synchronized static 方法和 synchronized (SomeClass.class)
     */
    @Test
    public void test6() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.method5()).start();
        new Thread(() -> forSync.method9()).start();
        Thread.sleep(6000);
    }

    /**
     * 可重入
     */
    @Test
    public void test7() throws Exception {
        ForSync forSync = new ForSync();
        forSync.method8();
        Thread.sleep(6000);
    }

    /**
     * synchronized (this.getClass()) 和 synchronized (ForSync.class)
     *
     */
    @Test
    public void test8() throws Exception {
        ForSync forSync = new ForSync();
        new Thread(() -> forSync.method9()).start();
        new Thread(() -> forSync.method10()).start();
        Thread.sleep(6000);
    }
}
