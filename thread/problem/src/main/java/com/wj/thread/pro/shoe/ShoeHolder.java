package com.wj.thread.pro.shoe;

import java.util.List;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:42
 */
public class ShoeHolder {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private List<Shoe> left;
    private List<Shoe> right;
    private volatile int next = LEFT;

    public ShoeHolder(List<Shoe> left, List<Shoe> right) {
        this.left = left;
        this.right = right;
    }

    public void addLeft(Shoe shoe) {
        left.add(shoe);
    }

    public void addRight(Shoe shoe) {
        right.add(shoe);
    }

    public void rmLeft() {
        left.remove(0);
    }

    public void rmRight() {
        left.remove(0);
    }

    public int leftSize() {
        return left.size();
    }

    public int rightSize() {
        return right.size();
    }

    public int getNext() {
        return next;
    }

    public void changeNext(int next) {
        this.next = next;
    }

    public boolean existPair() {
        return leftSize() > 0 && rightSize() > 0;
    }
}
