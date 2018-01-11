package com.wj.proxy.targets;

/**
 * @author : wangjia
 * @time : 2018/1/11 19:38
 */
public class RobotWorker implements Worker {
    @Override
    public void workPrepare() {
        System.out.println("workPrepare!");
    }

}
