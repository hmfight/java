package com.wj.thread.threadlocal;

/**
 * @author : wangjia
 * @time : 2018/1/10 19:42
 */
public class AddOneThread extends Thread {
    private SequenceNumber sn;

    public AddOneThread(SequenceNumber sn) {
        this.sn = sn;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
        }
    }
}
