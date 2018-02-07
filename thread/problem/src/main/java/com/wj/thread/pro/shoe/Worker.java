package com.wj.thread.pro.shoe;

import static com.wj.thread.pro.shoe.Shoe.LEFT;
import static com.wj.thread.pro.shoe.Shoe.RIGHT;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:37
 */
public class Worker extends Thread {
    private ShoeHolder shoeHolder;
    private int workerId;

    public Worker(ShoeHolder shoeHolder, int workerId) {
        this.shoeHolder = shoeHolder;
        this.workerId = workerId;
    }

    public void work() {
        run();
    }

    @Override
    public void run() {
        while (true) {
            if (shoeHolder.getNext() == LEFT) {
                productLeftShoe();
            } else {
                productRightShoe();
            }
        }
    }

    private void productLeftShoe() {
        System.out.println("worker " + workerId + " producting left shoe ");
        shoeHolder.addLeft(new Shoe(LEFT));
        sleep();
    }

    private void productRightShoe() {
        System.out.println("worker " + workerId + " producting right shoe ");
        shoeHolder.addRight(new Shoe(RIGHT));
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
