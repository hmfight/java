package com.wj.collection.test;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class TestListAndVector {
    /**
     * ArrayList 非线程安全
     */
    @Test
    public void testList() throws Exception {
        ArrayList<Integer> list = new ArrayList<>(10000);
        for (int i = 0; i < 10; i++) {
            new AddThread(list, 100).start();
        }
        Thread.sleep(3000);
        Assert.assertEquals(10000 != list.size(), true);
    }

    /**
     * Vector 线程安全
     */
    @Test
    public void testVector() throws Exception {
        Vector<Integer> vector = new Vector<>(10000);
        for (int i = 0; i < 10; i++) {
            new AddThread(vector, 100).start();
        }
        Thread.sleep(3000);
        Assert.assertEquals(1000, vector.size());
    }

    /**
     * 测试 同时做
     */
    @Test
    public void testVectorAddRm() throws Exception {
        Vector<Integer> vector = new Vector<>(10000);
        for (int i = 0; i < 100; i++) {
            new AddThread(vector, 100).start();
            new RmThread(vector, 50).start();
        }
        Thread.sleep(3000);
        Assert.assertEquals(5000, vector.size());
    }

    private class AddThread extends Thread {
        private List<Integer> list;
        private int num;

        public AddThread(List<Integer> list, int num) {
            this.list = list;
            this.num = num;
        }

        @Override
        public void run() {
            for (int j = 0; j < num; j++) {
                list.add(j);
            }
        }
    }

    private class RmThread extends Thread {
        private List<Integer> list;
        private int num;

        public RmThread(List<Integer> list, int num) {
            this.list = list;
            this.num = num;
        }

        @Override
        public void run() {
            for (int j = 0; j < num; j++) {
                if (!list.isEmpty()) {
                    list.remove(0);
                }
            }
        }
    }
}
