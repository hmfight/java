package com.wangjia.practice.concurrent.shoe;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:43
 */
public class Shoe {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private int whichLeg;

    public Shoe(int whichLeg) {
        this.whichLeg = whichLeg;
    }
}
