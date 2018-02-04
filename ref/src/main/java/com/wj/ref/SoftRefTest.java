package com.wj.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author :hmfight
 * @time : 2018/2/4 13:30
 */
public class SoftRefTest {
    private static ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>();

    static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) softQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("Object for SoftReference is " + obj.get());
            }
        }
    }

    public static void main(String[] args) {
        SoftReference<MyObject> softRef = new SoftReference<>(new MyObject(), softQueue);
        System.out.println("before GC: SoftRef.get()= " + softRef.get());
        new Thread(new CheckRefQueue()).start();
        System.out.println("Start GC");
        System.gc();
        System.out.println("After GC: SoftRef.get()= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[5 * 1024 * 745];
        System.out.println("After new byte[]:SoftRef.get()= " + softRef.get());
        System.gc();
    }
}
