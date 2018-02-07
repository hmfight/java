package com.wj.collection.test;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class TestListAndVector {
    @Test
    public void testList() throws Exception {
        ArrayList<Integer> list = new ArrayList<>(10000);
        for (int i = 0; i < 10; i++) {
            new AddThread(list).start();
        }
        Thread.sleep(3000);
        Assert.assertEquals(10000 != list.size(), true);
    }

    @Test
    public void testVector() throws Exception {
        Vector<Integer> vector = new Vector<>(10000);
        for (int i = 0; i < 10; i++) {
            new AddThread(vector).start();
        }
        Thread.sleep(3000);
        Assert.assertEquals(10000, vector.size());
    }

    private class AddThread extends Thread {
        private List<Integer> list;

        public AddThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                list.add(j);
            }
            System.out.println("add finished");
        }
    }
}
