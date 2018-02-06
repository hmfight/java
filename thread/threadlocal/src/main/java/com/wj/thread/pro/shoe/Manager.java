package com.wj.thread.pro.shoe;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:39
 */
public class Manager extends Thread {
    private ShoeHolder shoeHolder;

    public Manager(ShoeHolder shoeHolder) {
        this.shoeHolder = shoeHolder;
    }

    public void work() {
        run();
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(" manager work ");
            if (shoeHolder.leftSize() <= shoeHolder.rightSize()) {
                shoeHolder.changeNext(ShoeHolder.LEFT);
            } else {
                shoeHolder.changeNext(ShoeHolder.RIGHT);
            }
        }
    }
}
