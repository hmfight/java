package com.wj.thread.threadlocal;

/**
 * @author : wangjia
 * @time : 2018/1/10 19:44
 */
public class Main {
    public static void main(String[] args) {
        SequenceNumber seq = new SequenceNumber();
        AddOneThread t1 = new AddOneThread(seq);
        t1.start();
        AddOneThread t2 = new AddOneThread(seq);
        t2.start();
        AddOneThread t3 = new AddOneThread(seq);
        t3.start();

        while (true) {
            int i = 1;
        }
    }
}
