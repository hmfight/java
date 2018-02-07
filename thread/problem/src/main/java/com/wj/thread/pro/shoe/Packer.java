package com.wj.thread.pro.shoe;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:40
 */
public class Packer extends Thread {
    private ShoeHolder shoeHolder;

    public Packer(ShoeHolder shoeHolder) {
        this.shoeHolder = shoeHolder;
    }

    public void work() {
        run();
    }

    @Override
    public void run() {
        while (true) {
            if (shoeHolder.existPair()) {
                packPair(shoeHolder);
            } else {
                System.out.println(" no pair ");
            }
        }
    }

    private void packPair(ShoeHolder shoeHolder) {
        System.out.println("packer packing ");
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shoeHolder.rmLeft();
        shoeHolder.rmRight();
    }

}
