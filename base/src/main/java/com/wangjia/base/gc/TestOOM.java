package com.wangjia.base.gc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms50M -Xmx50M
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at Main$TestOOM.<init>(Main.java:9)
 * at Main.main(Main.java:15)
 */
@Slf4j
public class TestOOM {

    private static int counter = 0;

    static class BigObject {
        private byte[] temp = new byte[1024 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        List<BigObject> list = new ArrayList<>();
        while (true) {
            Thread.sleep(300);
            list.add(new BigObject());
            log.info("{}",counter++);
        }
    }
}