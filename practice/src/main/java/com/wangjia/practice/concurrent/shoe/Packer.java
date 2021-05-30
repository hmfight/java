package com.wangjia.practice.concurrent.shoe;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:40
 */
public class Packer extends Thread {
    private int nowPairs = 0;
    private ShoeHolder shoeHolder;

    public Packer(ShoeHolder shoeHolder) {
        this.shoeHolder = shoeHolder;
    }

    public void work() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            if (shoeHolder.existPair()) {
                packPair(shoeHolder);
            }
        }
    }

    private void packPair(ShoeHolder shoeHolder) {
        System.out.println("all shoe num:" + Worker.allShowNum.get() + "; packed:" + nowPairs + " pairs;" + " now left:"
                + shoeHolder.leftSize() + ", now right:" + shoeHolder.rightSize());
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nowPairs++;
        if (shoeHolder.existPair()) {
            shoeHolder.rmLeft();
            shoeHolder.rmRight();
        }
    }

}
