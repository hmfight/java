package com.wj.thread.pro.shoe;

import java.util.ArrayList;

/**
 * @author : wangjia
 * @time : 2018/2/6 13:34
 * pro:
 * 假设100个工人，其中98个生产鞋子，1个打包工人，另一个为主管。已知左鞋子的制作周期比右鞋子慢，打包必须得左右各取一只。
 * <p>
 * 思路：
 * 1、左右速率不一样，但是打包工人打包必须一对。主管需要动态协调工人应该生产左鞋还是右鞋，保证左右数量一致。
 * 2、100个工人是 100个单独的线程。
 * 3、两个缓冲区，分别存放左右鞋子。
 * 4、主管实时监控缓冲区，通知生产工人应该生产左鞋还是右鞋子；生产工人根据指示生产，放到对应缓存区；打包工人，不断从两个缓冲区取鞋子消费。
 * 5、注意点：多线程下缓存区数据结构的并发、进程间的通信（保证及时准确）。
 */
public class Starter {
    private static final int WORKER_NUM = 98;
//    private static final int MANAGER_NUM = 1;
//    private static final int PACKER_NUM = 1;

    public static void main(String[] args) throws InterruptedException {
        ShoeHolder shoeHolder = new ShoeHolder(new ArrayList<>(), new ArrayList<>());

        new Manager(shoeHolder).work();
        for (int i = 0; i < WORKER_NUM; i++) {
            new Worker(shoeHolder, i).work();
        }
        Thread.sleep(3000);
        new Packer(shoeHolder).work();
    }
}
