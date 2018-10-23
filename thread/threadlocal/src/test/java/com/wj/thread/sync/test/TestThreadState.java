package com.wj.thread.sync.test;

import com.wj.thread.state.MyThread;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : wangjia
 * @time : 2018/2/9 18:24
 */
public class TestThreadState {
    private static final byte[] lock = new byte[0];

    @Test
    public void testNew() throws Exception {
        MyThread myThread = new MyThread();
        Assert.assertEquals(Thread.State.NEW, myThread.getState());
    }

    @Test
    public void testRunable() throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();
        Assert.assertEquals(Thread.State.RUNNABLE, myThread.getState());
    }

    /**
     * 两个线程，先被调度的线程进入 RUNNABLE ,后一个线程进入 BLOCKED
     */
    @Test
    public void testBlock() throws Exception {
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Assert.assertEquals(Thread.State.RUNNABLE, thread1.getState());
        Assert.assertEquals(Thread.State.BLOCKED, thread2.getState());
    }

    /**
     *
     */
    @Test
    public void testWaiting() throws Exception {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        Thread.sleep(1000);
        Assert.assertEquals(Thread.State.BLOCKED, thread1.getState());
    }
}
