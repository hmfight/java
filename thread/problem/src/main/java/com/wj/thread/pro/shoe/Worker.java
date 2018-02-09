package com.wj.thread.pro.shoe;

import java.util.concurrent.atomic.AtomicLong;

import static com.wj.thread.pro.shoe.Shoe.LEFT;
import static com.wj.thread.pro.shoe.Shoe.RIGHT;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:37
 */
public class Worker extends Thread {
    public static volatile AtomicLong allShowNum = new AtomicLong();

    private ShoeHolder shoeHolder;
    private int workerId;

    public Worker(ShoeHolder shoeHolder, int workerId) {
        this.shoeHolder = shoeHolder;
        this.workerId = workerId;
    }

    public void work() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            if (shoeHolder.getNext() == LEFT) {
                productLeftShoe();
                sleep(3);
            } else {
                productRightShoe();
                sleep(2);
            }
        }
    }

    private void productLeftShoe() {
        System.out.println("worker " + workerId + " are producting shoe(left) ");
        shoeHolder.addLeft(new Shoe(LEFT));
        allShowNum.addAndGet(1);
    }

    private void productRightShoe() {
        System.out.println("worker " + workerId + " are producting show(right) ");
        shoeHolder.addRight(new Shoe(RIGHT));
        allShowNum.addAndGet(1);
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
