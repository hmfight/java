package com.wj.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * from : http://blog.csdn.net/u013256816/article/details/50907595
 *
 * @time : 2018/2/4 13:30
 */
public class WeakRefTest {
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
                System.out.println("Object for WeakReference is " + obj.get());
            }
        }
    }

    public static void main(String[] args) {
        WeakReference<MyObject> softRef = new WeakReference<>(new MyObject(), softQueue);
        System.out.println("before GC: WeakRef.get()= " + softRef.get());
        new Thread(new CheckRefQueue()).start();
        System.out.println("Start GC");
        System.gc();
        System.out.println("After GC: WeakRef.get()= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[5 * 1024];
        System.out.println("After new byte[]:WeakRef.get()= " + softRef.get());
        System.gc();
    }
}
