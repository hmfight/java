package com.wangjia.thread.sync;

/**
 * @author : wangjia
 * @time : 2018/2/8 18:22
 */
public class ForSync {
    private final byte[] lock = new byte[0];

    public synchronized void syncMethod1() {
        System.out.println("syncMethod1 get lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("syncMethod1 free lock");
    }

    public synchronized void syncMethod2() {
        System.out.println("syncMethod2 get lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("syncMethod2 free lock");
    }

    public void method3() {
        System.out.println("method3");
    }

    public static void method4() {
        System.out.println("static method3");
    }

    public synchronized static void method5() {
        System.out.println("synchronized static method5() get lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synchronized static method5() free lock");
    }

    public void method6() {
        synchronized (this) {
            System.out.println("synchronized(this) method6() get lock");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized(this) method6() free lock");
        }
    }

    public void method7() {
        synchronized (lock) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized static method7");
        }
    }

    public synchronized void method8() {
        System.out.println("syncMethod8 get lock");
        this.syncMethod1();
        System.out.println("syncMethod get lock");
    }

    public void method9() {
        synchronized (ForSync.class) {
            System.out.println("synchronized(ForSync.class) method9 get lock");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized(ForSync.class) method9 free lock");
        }
    }

    public void method10() {
        synchronized (this.getClass()) {
            System.out.println("synchronized(getClass) method10 get lock");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized(getClass) method10 free lock");
        }
    }
}
